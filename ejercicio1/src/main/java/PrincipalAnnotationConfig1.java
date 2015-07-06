import gui.CalculadoraGui;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.Config1;


public class PrincipalAnnotationConfig1 {
	public static void main(String[] args) {
		new AnnotationConfigApplicationContext(Config1.class)
			.getBean("calculadoraGui2", CalculadoraGui.class)
			.iniciar();
	}

}
