package at.aygu.test.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;

import at.aygu.Application;

/**
 * Use this annotation to configure a test class for integration tests.
 * Also the profile will be set to 'test' to use application-test.properties if available.
 * @author guersel
 *
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles(profiles = "test")
public @interface TestConfiguration {

}
