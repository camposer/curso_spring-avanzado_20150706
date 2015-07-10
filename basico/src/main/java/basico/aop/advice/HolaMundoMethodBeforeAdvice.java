package basico.aop.advice;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class HolaMundoMethodBeforeAdvice 
		implements MethodBeforeAdvice {

	public void before(Method metodo, Object[] args, Object objeto)
			throws Throwable {
		System.out.println("Método: " + metodo);
		System.out.println("Argumentos: " + args);
		System.out.println("Objeto: " + objeto);
	}

}
