package skibidi.bop.api.create_hardware;

import skibidi.bop.api.create_hardware.payload.Payload;
import skibidi.bop.api.create_hardware.response.CreateHardwareResponse;
import skibidi.bop.core.Request;
import skibidi.bop.core.annotations.BaseUri;
import skibidi.bop.core.annotations.Endpoint;
import skibidi.bop.core.annotations.Method;

@BaseUri(name = "https://api.restful-api.dev")
@Endpoint(name = "/objects")
@Method(method = Request.Method.POST)
public class CreateHardware extends Request {

    {
        setBody(new Payload());
        setResponseClass(CreateHardwareResponse.class);
    }
}
