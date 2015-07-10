package basico.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import basico.HolaMundo;

public class Principal {
	public static void main(String[] args) {
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("applicationContext-aop.xml");
		
		HolaMundo holaMundo = ctx.getBean(HolaMundo.class);
		System.out.println("saludar() " + holaMundo.saludar());
		System.out.println("saludar(String) " + holaMundo.saludar("Juan"));
	}
}
