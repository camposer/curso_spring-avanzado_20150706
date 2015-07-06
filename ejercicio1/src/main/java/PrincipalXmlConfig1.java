import org.springframework.context.support.ClassPathXmlApplicationContext;

import gui.CalculadoraGui;


public class PrincipalXmlConfig1 {
	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("applicationContext1.xml")
			.getBean(CalculadoraGui.class)
			.iniciar();
	}

}
