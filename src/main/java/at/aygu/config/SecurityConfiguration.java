package at.aygu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:at/aygu/spring-security-config.xml")
public class SecurityConfiguration {

}
