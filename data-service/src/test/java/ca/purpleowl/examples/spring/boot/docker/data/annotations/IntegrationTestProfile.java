package ca.purpleowl.examples.spring.boot.docker.data.annotations;

import org.springframework.context.annotation.Profile;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * We use this annotation to eliminate chances of a typo pointing to the wrong profile, and to allow easier refactoring,
 * should we decide to change the profile used, etc...
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Profile("integration")
public @interface IntegrationTestProfile {
}
