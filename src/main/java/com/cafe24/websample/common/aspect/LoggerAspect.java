package com.cafe24.websample.common.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {
    //protected Logger log = LoggerFactory.getLogger(LoggerAspect.class);
    private static final Logger log = LoggerFactory.getLogger(LoggerAspect.class);
    static String name = "";
    static String type = "";

	//@Before("@annotation(com.cafe24.websample.common.annotation.LogAround)")
	public void logBefore(JoinPoint joinPoint) {
		log.debug("logBefore()");
	}

	public void logAfter(JoinPoint joinPoint) {
		log.debug("logAfter()");
	}

    //@Around("execution(* com.cafe24.websample..*Control*(..)) or execution(* com.cafe24.websample..*Service*(..)) or execution(* com.cafe24.websample..*Dao*(..))")
	//@Around("execution(* com.cafe24.websample.web.*.*(..))")
	@Around("@annotation(com.cafe24.websample.common.annotation.ProgressTime)")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
    	Object thisObj = joinPoint.getTarget();
		String className = thisObj.getClass().getName();
		Object[] args = joinPoint.getArgs();

		long currentTime = System.currentTimeMillis();

		//if (log.isDebugEnabled()) {
			log.info("=================================================");
			log.info("START | " + className + "|" + joinPoint.getSignature().getName());

/*			log.debug("[class]:" + className);
			log.debug("[method]:" + joinPoint.getSignature().getName() + "()");*/
		//}

		Object returnObj = joinPoint.proceed();
		
		//if (log.isDebugEnabled()) {
/*			log.info("[class]:" + className);
			log.info("[method]:" + joinPoint.getSignature().getName() + "()");*/
			log.info("[소요시간]: {}ms", new Object[] { (System.currentTimeMillis() - currentTime) });
			log.info("END | " + className + "|" + joinPoint.getSignature().getName());
			log.info("=================================================");
		//}

		return returnObj;

    }

	//모든 Service Class
	@Around("execution(* com..*Service.*(..))")
	public Object commonServiceLogging(ProceedingJoinPoint joinPoint) throws Throwable {

		long currentTime = System.currentTimeMillis();
		String className = joinPoint.getSignature().getDeclaringTypeName();
		String methodName = joinPoint.getSignature().getName();

		log.debug("=================================================");
		log.debug(">>>>>>>>> LOGGING START >>>>>>>>>>");
		log.debug("[class]:{}", className);
		log.debug("[method]:{}()", methodName);

		Object result = joinPoint.proceed();

		//log.debug("[class]:{}", className);
		//log.debug("[method]:{}()", methodName);
		log.debug("[소요시간]: {}ms", new Object[]{(System.currentTimeMillis()-currentTime)});
		log.debug(">>>>>>>>>> LOGGING END >>>>>>>>>>");
		log.debug("=================================================");

		return result;
	}

	@Pointcut("execution(public * com..*Controller.*(..))")
	public void controllerClassMethods() {}

	@Before(value = "controllerClassMethods()")
	public void checkSessionValid(JoinPoint joinPoint) {
		String token = joinPoint.getSignature().getDeclaringTypeName(); //JoinPoint클래스를 이용하여 타켓 메서드의 파라미터를 가로챌 수 있다.
		log.debug("Controller Before AOP >>>>>>>>>>>>>>>  {}", token);
	}



}