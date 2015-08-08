package at.aygu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class MessageBundleConfiguration {
	
	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("Messages");
		return messageSource;
	}
	
	@Bean
	public ResourceBundleMessageSource validationMessageSource() {
		ResourceBundleMessageSource validationMessageSource = new ResourceBundleMessageSource();
		validationMessageSource.setBasename("ValidationMessages");
		validationMessageSource.setFallbackToSystemLocale(false);
		return validationMessageSource;
	}
}
