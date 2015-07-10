package basico.scheduler;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class HolaMundoScheduler {

	@Scheduled(fixedRate = 1000)
	public void holaMundo() {
		System.out.println("Hola mundo");
	}

	@Configuration
	@EnableScheduling
	@ComponentScan
	public static class Config {
		
	}
}
