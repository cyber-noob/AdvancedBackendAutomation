package skibidi.bop.core;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.filter.Filter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import skibidi.bop.core.interfaces.ApiPart;
import skibidi.bop.core.annotations.BaseUri;
import skibidi.bop.core.annotations.Endpoint;
import skibidi.bop.core.annotations.Method;

import java.util.Map;

public class RequestMaker {

    static {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    public static Response createRequest(Request request) {

        BaseUri baseUri = request.getClass().getAnnotation(BaseUri.class);
        Endpoint endpoint = request.getClass().getAnnotation(Endpoint.class);
        Method method = request.getClass().getAnnotation(Method.class);

        if (baseUri.name().isEmpty())
            throw new RuntimeException("Please set a baseUri before executing the request");

        switch (method.method()) {
            case GET -> {
                return RestAssured.given()
                        .header("Content-Type", ContentType.JSON)
                        .headers(mapItems(request.getHeaders()))
//                        .queryParams(request.getPathParams().create())
                        .queryParams(mapItems(request.getQueryParams()))
                        .get(baseUri.name() + endpoint.name())
                        .then()
                        .extract()
                        .response();
            }

            case POST -> {
                return RestAssured.given()
                        .header("Content-Type", ContentType.JSON)
                        .headers(mapItems(request.getHeaders()))
                        .pathParams(mapItems(request.getPathParams()))
                        .queryParams(mapItems(request.getQueryParams()))
                        .body(mapItems(request.getBody()))
                        .post(baseUri.name() + endpoint.name())
                        .then()
                        .extract()
                        .response();
            }

            default -> throw new RuntimeException("Please select a valid method to execute");
        }
    }

    private static Map<String, Object> mapItems(ApiPart apiPart) {
        if (apiPart.getClass().getDeclaredFields().length == 0)
            return Map.of();

        return new ObjectMapper().convertValue(apiPart, new TypeReference<>() {});
    }
}
