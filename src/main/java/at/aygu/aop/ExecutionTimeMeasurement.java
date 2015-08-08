package at.aygu.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * Time measurement aspect.
 * 
 * @author guersel
 *
 */
@Component
@Aspect
public class ExecutionTimeMeasurement {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ExecutionTimeMeasurement.class);

	/**
	 * Measure execution time on repository method calls.
	 * 
	 * @param pjp
	 *            {@link ProceedingJoinPoint}
	 * @return the method return value
	 * @throws Throwable
	 *             if any error occurs during method execution
	 */
	@Around("within(at.aygu.db.repository.*)")
	public Object timeMeasurement(ProceedingJoinPoint pjp) throws Throwable {
		StopWatch watch = new StopWatch();
		watch.start();

		Object returnValue = pjp.proceed();

		watch.stop();

		String className = pjp.getTarget().getClass().getName();
		String methodName = pjp.getSignature().getName();
		LOGGER.info("{} {}ms elapsed", className + "." + methodName,
				watch.getTotalTimeMillis());

		return returnValue;
	}
}
