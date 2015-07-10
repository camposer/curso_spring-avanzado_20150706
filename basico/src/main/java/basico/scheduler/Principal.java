package basico.scheduler;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import basico.scheduler.HolaMundoScheduler.Config;

public class Principal {

	public static void main(String[] args) {
		ApplicationContext ctx = 
				new AnnotationConfigApplicationContext(Config.class);
		ctx.getBean(HolaMundoScheduler.class);
	}
	
}
