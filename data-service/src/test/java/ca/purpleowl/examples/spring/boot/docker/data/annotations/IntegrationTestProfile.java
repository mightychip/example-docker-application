package ca.purpleowl.examples.spring.boot.docker.data.annotations;

import org.springframework.context.annotation.Profile;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * We use this annotation to eliminate chances of a typo pointing to the wrong profile, and to allow easier refactoring,
 * should we decide to change the profile used, etc.
 *
 * Basically, the idea is that if we have any integration tests that actually require a DB to be present to run, we can
 * just use this annotation to have only those tests run against an H2 database.  You could similarly use an annotation
 * like this to activate a profile that points to a DB already loaded with testing data.
 *
 * Alternately, you could just specify the profile in the spring-boot-maven plugin, but that may mean the chosen
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Profile("integration")
public @interface IntegrationTestProfile {
}
