package advice;

import java.io.File;
import java.io.PrintWriter;
import java.util.Date;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class LoggerMethodInterpcetor implements MethodInterceptor {
	private String loggerFilePath;
	
	public LoggerMethodInterpcetor(String loggerFilePath) {
		this.loggerFilePath = loggerFilePath;
	}

	@Override
	public Object invoke(MethodInvocation metodo) throws Throwable {
		PrintWriter pw = new PrintWriter(new File(loggerFilePath));
		pw.println(getTrace(metodo));
		pw.close();
		
		return metodo.proceed();
	}

	private String getTrace(MethodInvocation metodo) {
		String nombreMetodo = metodo.getMethod().getName();
		
		String argumentos = "[ ";
		if (metodo.getArguments() != null) for (Object a : metodo.getArguments()) {
			argumentos += a + " ";
		}
		argumentos += "]";
		
		long marcaTiempo = new Date().getTime();
		
		return "[ método = " + nombreMetodo +
				", argumentos = " + argumentos + 
				", " + marcaTiempo + " ]";
	}

}
