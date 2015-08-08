package at.aygu.config;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

@Configuration
@AutoConfigureAfter(ThymeleafAutoConfiguration.class)
public class ThymeleafConfiguration {
	
	@Resource
	private ITemplateResolver thymeleafResolver;
	
	@Value("${thymeleaf.template.cache}")
	private boolean thymeleafTemplateCache;
	
	@PostConstruct
	public void updateTymeleafConfiguration() {
		TemplateResolver resolver = (TemplateResolver) thymeleafResolver;
		resolver.setCacheable(thymeleafTemplateCache);
	}
}
