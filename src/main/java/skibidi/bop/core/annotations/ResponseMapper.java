package skibidi.bop.core.annotations;

import lombok.NonNull;

import java.lang.annotation.*;

@Repeatable(ResponseMapper.List.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ResponseMapper {

    @NonNull
    String endpoint();

    @NonNull
    String jsonPath();

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.FIELD})
    @interface List {
        ResponseMapper[] value();
    }
}
