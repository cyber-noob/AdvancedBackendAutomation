package skibidi.bop.core.interfaces;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public interface ApiPart {

    default Map<String, Object> create() throws InstantiationException, IllegalAccessException {

        if (this.getClass().getDeclaredFields().length == 0)
            return Map.of();

        return new ObjectMapper().convertValue(
                this.getClass().newInstance(),
                new TypeReference<>() {
                });
    }
}
