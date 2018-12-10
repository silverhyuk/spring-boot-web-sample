package com.cafe24.websample.common.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LoggerAspect {
    //protected Logger logger = LoggerFactory.getLogger(LoggerAspect.class);
    private static final Logger logger = LoggerFactory.getLogger(LoggerAspect.class);
    static String name = "";
    static String type = "";

	public void logBefore(JoinPoint joinPoint) {
		logger.debug("logBefore()");
	}

	public void logAfter(JoinPoint joinPoint) {
		logger.debug("logAfter()");
	}

    //@Around("execution(* kr.co.wcorp..*Control*(..)) or execution(* kr.co.wcorp..*Service*(..)) or execution(* kr.co.wcorp..*Dao*(..))")
	//@Around("execution(* kr.co.wcorp..*Service.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
    	Object thisObj = joinPoint.getTarget();
		String className = thisObj.getClass().getName();
		Object[] args = joinPoint.getArgs();

		long currentTime = System.currentTimeMillis();

		//if (logger.isDebugEnabled()) {
			logger.info("=================================================");
			logger.info("START | " + className + "|" + joinPoint.getSignature().getName());
/*			logger.debug("[class]:" + className);
			logger.debug("[method]:" + joinPoint.getSignature().getName() + "()");*/
		//}

		Object returnObj = joinPoint.proceed();
		
		//if (logger.isDebugEnabled()) {
/*			logger.info("[class]:" + className);
			logger.info("[method]:" + joinPoint.getSignature().getName() + "()");*/
			logger.info("[소요시간]: {}ms", new Object[] { (System.currentTimeMillis() - currentTime) });
			logger.info("END | " + className + "|" + joinPoint.getSignature().getName());
			logger.info("=================================================");
		//}

		return returnObj;

    }
}