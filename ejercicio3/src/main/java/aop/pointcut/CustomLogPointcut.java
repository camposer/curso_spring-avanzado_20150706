package aop.pointcut;

import java.lang.reflect.Method;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.beans.factory.annotation.Autowired;

import annotation.CustomLog;
import aop.advice.CustomLogMethodInterpcetor;

public class CustomLogPointcut extends AbstractPointcutAdvisor {
	private static final long serialVersionUID = -686197170293275356L;

	@Autowired
	private CustomLogMethodInterpcetor customLogMethodInterpcetor; 

	@Override
	public Pointcut getPointcut() {
		return new StaticMethodMatcherPointcut() {
            @Override
            public boolean matches(Method method, Class<?> targetClass) {
                return method.isAnnotationPresent(CustomLog.class);
            }
        };
	}

	@Override
	public Advice getAdvice() {
		return customLogMethodInterpcetor;
	}

}
