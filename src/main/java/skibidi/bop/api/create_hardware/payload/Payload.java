package skibidi.bop.api.create_hardware.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import skibidi.bop.core.interfaces.ApiPart;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Payload implements ApiPart {

    private String name = "Apple MacBook Pro 16";

    private skibidi.bop.api.create_hardware.payload.Data data = new skibidi.bop.api.create_hardware.payload.Data();
}
