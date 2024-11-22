package skibidi.bop.core;

import io.restassured.filter.Filter;
import lombok.Builder;
import skibidi.bop.core.interfaces.IReporter;

@Builder
public class Reporter {

    private Filter filter;

    private IReporter<?> reporter;
}
