package com.douzone.mysite.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class MeasureExecutionTimeAspect {
	
	@Around("execution(* *..repository.*.*(..)) || execution(* *..service.*.*(..)) || execution(* *..controller.*.*(..))")
	public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable{
		// before
		// StopWatch
		// 기존 코드 변경
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
	
		
		// method 실행
		Object result = pjp.proceed();
				
		// after
		stopWatch.stop();
		Long totalTime = stopWatch.getTotalTimeMillis();
		
		// 클래스 정보를 가져온다.
		// Class Name 가져오기
		String className = pjp.getTarget().getClass().getName();
		// Method 가져오기
		String methodName = pjp.getSignature().getName();
		// 출력--
		String taskName = className + "." + methodName;
		
		System.out.println("[ExcutionTime][" + taskName + "] " + totalTime + "mils");
		
		
		
		return result;
	}
}
