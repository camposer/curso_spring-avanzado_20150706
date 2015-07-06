package basico;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Principal {
	public static void main(String[] args) {
		//HolaMundo hm = HolaMundoFactory.createHolaMundo();
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		
		HolaMundo hm1 = ctx.getBean("holaMundo", HolaMundo.class);
		HolaMundo hm2 = ctx.getBean("helloWorld", HolaMundo.class);
		
		System.out.println(hm1.saludar("Juan")); // => Hola Juan
		System.out.println(hm2.saludar("Juan")); // => Hello Juan
		System.out.println(hm1.saludar()); // => [ Pedro ]
		
		ctx.getBean("envoltorio", Envoltorio.class).setNombre("María");
		System.out.println(ctx.getBean("holaMundo", HolaMundo.class)
				.saludar()); // => [ María ]
		

	}
}
