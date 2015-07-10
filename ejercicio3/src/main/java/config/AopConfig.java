package config;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import aop.advice.CustomLogMethodInterpcetor;
import aop.pointcut.CustomLogPointcut;
import dao.PersonaDao;

@Configuration
@PropertySource("classpath:app.properties")
public class AopConfig {
	@Value("#{personaDaoImpl}") // spEL
	private PersonaDao personaDao;
	@Value("${logger.file.path}") // propiedad
	private String loggerFilePath;
	
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
    	DefaultAdvisorAutoProxyCreator advisor = 
    			new DefaultAdvisorAutoProxyCreator();
    	advisor.setProxyTargetClass(true);
    	return advisor;
    }
    
	@Bean
	public CustomLogPointcut customLogPointcut() {
		return new CustomLogPointcut();
	}

	@Bean
	public CustomLogMethodInterpcetor customLogMethodInterceptor() {
		return new CustomLogMethodInterpcetor(loggerFilePath);
	}
	
	@Bean
	public ProxyFactoryBean personaDaoProxy() {
		ProxyFactoryBean proxy = new ProxyFactoryBean();
		proxy.setTarget(personaDao);
		proxy.setInterceptorNames("customLogMethodInterceptor");
		
		return proxy;
	}
}
