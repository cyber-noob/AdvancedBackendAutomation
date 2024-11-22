package skibidi.bop.api.create_hardware.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import skibidi.bop.api.create_hardware.payload.Payload;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateHardwareResponse extends Payload {

    private String id;

    private String createdAt;
}
