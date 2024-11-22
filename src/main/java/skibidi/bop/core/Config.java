package skibidi.bop.core;

import lombok.Builder;
import skibidi.bop.core.interfaces.ILogger;
import skibidi.bop.core.interfaces.IMapper;

import java.util.List;

@Builder
public class Config {

    private List<ILogger> logger;

    private List<IMapper> ResponseMapper;
}
