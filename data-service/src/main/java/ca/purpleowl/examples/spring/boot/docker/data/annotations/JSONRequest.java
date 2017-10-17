package ca.purpleowl.examples.spring.boot.docker.data.annotations;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * I'm not big on clutter, and when you start using a boatload of Annotations with a boatload of parameters,
 * you end up with a lot of clutter.  What I've done here is use the magic of {@link org.springframework.core.annotation.AliasFor} to create
 * a single annotation to create boilerplate Annotation clutter chunks.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@RequestMapping(produces = "application/json", consumes = "application/json")
@interface JSONRequest {
    @AliasFor(annotation = RequestMapping.class, attribute = "name")
    String name() default "";

    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default{};

    @AliasFor(annotation = RequestMapping.class, attribute = "method")
    RequestMethod[] method() default{};

    @AliasFor(annotation = RequestMapping.class, attribute = "params")
    String[] params() default{};

    @AliasFor(annotation = RequestMapping.class, attribute = "headers")
    String[] headers() default{};
}
