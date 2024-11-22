package skibidi.bop.core;

import lombok.*;
import skibidi.bop.core.interfaces.ApiPart;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Request<T> {

    private ApiPart headers = new EmptyClass();

    private ApiPart queryParams = new EmptyClass();

    private ApiPart pathParams = new EmptyClass();

    private ApiPart body = new EmptyClass();

    private T responseClass;

    static class EmptyClass implements ApiPart {

    }

    public enum Method {
        GET,

        POST,

        PUT,

        DELETE
    }
}
