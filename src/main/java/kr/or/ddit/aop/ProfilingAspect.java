package kr.or.ddit.aop;

import java.text.SimpleDateFormat;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class ProfilingAspect {
	private static final Logger logger = LoggerFactory.getLogger(ProfilingAspect.class);

	@Pointcut("execution(* kr.or.ddit..service.*.*(..))")
	public void dummy() {
	}

	@Around("dummy()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		SimpleDateFormat sdf = new SimpleDateFormat();

		// business logic 실행전
		long beforeTime = System.currentTimeMillis();
		logger.debug("Logging Aspect around method before:{}", beforeTime);
		// business logic 실행
		Object[] methodArgs = joinPoint.getArgs();
		Object returnObj = joinPoint.proceed(methodArgs);
		// business logic 실행후
		long afterTime = System.currentTimeMillis();
		logger.debug("Logging Aspect around method after:{}", afterTime);
		logger.debug("Logging Aspect arountd method afterTime-beforeTime defference:{}", afterTime - beforeTime);
		return returnObj;
	}

}
