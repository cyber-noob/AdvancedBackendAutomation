package skibidi.bop.core;

import io.restassured.response.Response;
import lombok.Builder;
import skibidi.bop.core.annotations.*;
import skibidi.bop.core.interfaces.ApiPart;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;

public class RequestProcessor {

    private Map<String, Response> responseMap = new ConcurrentHashMap<>();

    public Response process(Queue<Request> requestQueue) throws ExecutionException, InterruptedException {
        AtomicReference<Response> result = new AtomicReference<>();
        AtomicReference<Request> request = new AtomicReference<>(requestQueue.poll());
        CompletableFuture<Response> promise = CompletableFuture.supplyAsync(() -> RequestMaker.createRequest(request.get()));

        CompletableFuture<Response> handleFuture = promise
                .exceptionally(ex -> {
                    System.err.println("Exception: " + ex.getMessage());
                    throw new RuntimeException(ex);
                });

        System.out.println("q size: " + requestQueue.size());
        for (Request nextRequest : requestQueue) {
            System.out.println("q size: " + requestQueue.size());

            handleFuture
                    .thenApply(response -> {
                        System.out.println("debug: " + requestQueue.size());

                        ApiPart headers = nextRequest.getHeaders();
                        ApiPart queryParams = nextRequest.getQueryParams();
                        ApiPart pathParams = nextRequest.getPathParams();

                        String baseUri = request.get()
                                .getClass()
                                .getAnnotation(BaseUri.class)
                                .name();

                        String endpoint = request.get()
                                .getClass()
                                .getAnnotation(Endpoint.class)
                                .name();

                        String method = request.get()
                                .getClass()
                                .getAnnotation(Method.class)
                                .method()
                                .name();

                        responseMap.put(method + "-" + baseUri + endpoint, response);
                        System.out.println("debug: " + responseMap);

                        nextRequest.setHeaders(modFields(headers, endpoint, response));
                        nextRequest.setQueryParams(modFields(queryParams, endpoint, response));
                        nextRequest.setPathParams(modFields(pathParams, endpoint, response));

                        request.set(nextRequest);
                        return RequestMaker.createRequest(nextRequest);
                    });
        }

        assert handleFuture != null;
        handleFuture.thenAccept(response -> {
            result.set(response);
            System.out.println("Transformation complete...");
        });
        handleFuture.get();
        System.out.println("map: " + responseMap);
        return result.get();
    }

    private static ApiPart modFields(ApiPart apiPart, String endpoint, Response response) {

        AtomicReference<ApiPart> result = new AtomicReference<>(apiPart);

        Arrays.stream(
                apiPart.getClass()
                        .getDeclaredFields()
                )
                .toList()
                .forEach(field -> {
                    System.out.println("field: " + field);
                    if (field.isAnnotationPresent(ResponseMapper.class)) {
                        ResponseMapper responseMapper = field.getAnnotation(ResponseMapper.class);
                        boolean isEndpoint = responseMapper.endpoint()
                                .equals(endpoint);

                        if (isEndpoint)
                            result.set(modRequest(apiPart, field, response, responseMapper.jsonPath()));
                    }

                    else
                        System.out.println("No annotation to process");
                });

        return result.get();
    }

    private static ApiPart modRequest(ApiPart apiPart, Field field, Response response, String mapper) {
        field.setAccessible(true);
        try {
            field.set(apiPart, response.body().jsonPath().get(mapper));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return apiPart;
    }
}
