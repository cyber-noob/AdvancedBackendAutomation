package skibidi.bop.api.fetch_hardware.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import skibidi.bop.api.create_hardware.response.CreateHardwareResponse;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FetchHardwareByIdResponse {

    private List<CreateHardwareResponse> response;
}
