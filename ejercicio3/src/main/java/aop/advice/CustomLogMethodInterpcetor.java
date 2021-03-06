package aop.advice;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Date;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class CustomLogMethodInterpcetor implements MethodInterceptor {
	private String loggerFilePath;
	
	public CustomLogMethodInterpcetor(String loggerFilePath) {
		this.loggerFilePath = loggerFilePath;
	}

	@Override
	public Object invoke(MethodInvocation metodo) throws Throwable {
		Files.write(new File(loggerFilePath).toPath(), 
				getTrace(metodo).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
		
		return metodo.proceed();
	}

	private String getTrace(MethodInvocation metodo) {
		String nombreClase = metodo.getThis().getClass().getName();

		String nombreMetodo = metodo.getMethod().getName();
		
		String argumentos = "[ ";
		if (metodo.getArguments() != null) for (Object a : metodo.getArguments()) {
			argumentos += a + " ";
		}
		argumentos += "]";
		
		long marcaTiempo = new Date().getTime();
		
		return "[ clase = " + nombreClase + 
				", método = " + nombreMetodo +
				", argumentos = " + argumentos + 
				", " + marcaTiempo + " ]\n";
	}

}
