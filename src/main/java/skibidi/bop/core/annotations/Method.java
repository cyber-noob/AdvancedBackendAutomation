package skibidi.bop.core.annotations;

import lombok.NonNull;
import skibidi.bop.core.Request;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Method {

    @NonNull
    Request.Method method();
}
