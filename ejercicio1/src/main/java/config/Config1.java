package config;

import gui.CalculadoraGui;
import gui.CalculadoraGuiImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import service.CalculadoraService;
import service.CalculadoraServiceImpl;

@Configuration
// <bean id="config" class="config.Config/>
public class Config1 {
	@Bean
	// <bean id="calculadoraService" class="service.CalculadoraServiceImpl/>
	public CalculadoraService calculadoraService1() {
		return new CalculadoraServiceImpl();
	}

	@Bean
	public CalculadoraService calculadoraService2() {
		return new CalculadoraServiceImpl();
	}

	@Bean(name = "calculadoraGui1")
	@Scope("prototype")
	// <bean id="calculadoraGui" class="service.CalculadoraGuiImpl/>
	public CalculadoraGui getCalculadoraGui() {
		CalculadoraGuiImpl calculadoraGui = new CalculadoraGuiImpl();
		calculadoraGui.setCalculadoraService(calculadoraService1()); // Saca el bean del contexto de Spring y lo inyecta!
		
		return calculadoraGui;
	}

	@Bean
	public CalculadoraGui calculadoraGui2() {
		return new CalculadoraGuiImpl();
	}
}
