package ca.purpleowl.examples.spring.boot.docker.data.annotations;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * See {@link JSONRequest} for a little more information on what we're doing here.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@JSONRequest(method = RequestMethod.DELETE)
public @interface JSONDELETE {
    @AliasFor(annotation = JSONRequest.class, attribute = "name")
    String name() default "";

    @AliasFor(annotation = JSONRequest.class, attribute = "path")
    String[] path() default{};

    @AliasFor(annotation = JSONRequest.class, attribute = "params")
    String[] params() default{};

    @AliasFor(annotation = JSONRequest.class, attribute = "headers")
    String[] headers() default{};
}
