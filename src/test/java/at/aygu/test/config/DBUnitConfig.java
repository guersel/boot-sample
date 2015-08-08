package at.aygu.test.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.dbunit.DataSourceDatabaseTester;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBUnitConfig {
	
	@Resource
	private DataSource dataSource;
	
	@Bean
	public DataSourceDatabaseTester dbTester() {
		return new DataSourceDatabaseTester(dataSource);
	}
}
