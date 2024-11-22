package skibidi.bop.api.fetch_hardware;

import lombok.Data;
import skibidi.bop.api.fetch_hardware.response.FetchHardwareByIdResponse;
import skibidi.bop.core.Request;
import skibidi.bop.core.annotations.*;
import skibidi.bop.core.interfaces.ApiPart;

@BaseUri(name = "https://api.restful-api.dev")
@Endpoint(name = "/objects")
@Method(method = Request.Method.GET)
public class FetchHardwareById extends Request {

    {
        setQueryParams(new Query());
        setResponseClass(FetchHardwareByIdResponse.class);
    }

    @Data
    public static class Query implements ApiPart {

        @ResponseMapper(endpoint = "/objects", jsonPath = "id")
        private String id = "1";
    }
}
