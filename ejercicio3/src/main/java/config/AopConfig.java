package config;

import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import advice.LoggerMethodInterpcetor;
import dao.PersonaDao;

@Configuration
@PropertySource("classpath:app.properties")
public class AopConfig {
	@Value("#{personaDaoImpl}") // spEL
	private PersonaDao personaDao;
	@Value("${logger.file.path}") // propiedad
	private String loggerFilePath;
	
	@Bean
	public MethodInterceptor loggerMethodInterpcetor() {
		return new LoggerMethodInterpcetor(loggerFilePath);
	}
	
	@Bean
	public ProxyFactoryBean personaDaoProxy() {
		ProxyFactoryBean proxy = new ProxyFactoryBean();
		proxy.setTarget(personaDao);
		proxy.setInterceptorNames("loggerMethodInterpcetor");
		
		return proxy;
	}
	
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
       return new PropertySourcesPlaceholderConfigurer();
    }	
}
