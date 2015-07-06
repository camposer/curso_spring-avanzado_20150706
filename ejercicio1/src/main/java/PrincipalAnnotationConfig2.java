import gui.CalculadoraGui;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.Config2;


public class PrincipalAnnotationConfig2 {
	public static void main(String[] args) {
		new AnnotationConfigApplicationContext(Config2.class)
			.getBean(CalculadoraGui.class)
			.iniciar();
	}

}
