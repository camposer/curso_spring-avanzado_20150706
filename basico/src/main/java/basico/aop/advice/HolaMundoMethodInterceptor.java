package basico.aop.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import basico.HolaMundo;

public class HolaMundoMethodInterceptor 
		implements MethodInterceptor {

	public Object invoke(MethodInvocation metodo) throws Throwable {
		System.out.println("Nombre del método: " + metodo.getMethod().getName());
		
		// Obviando el método saludar(String)
		if (metodo.getMethod()
				.equals(HolaMundo.class.getMethod("saludar", String.class)))
			return null;
		
		
		return metodo.proceed();
	}

}
