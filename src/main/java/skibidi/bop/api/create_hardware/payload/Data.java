package skibidi.bop.api.create_hardware.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@lombok.Data
public class Data {

    private Integer year = 2016;

    private Double price = 920.32;

    @JsonProperty("CPU model")
    private String cpuModel = "Intel Core i9";

    @JsonProperty("Hard disk size")
    private String hardDiskSize = "1 TB";
}
