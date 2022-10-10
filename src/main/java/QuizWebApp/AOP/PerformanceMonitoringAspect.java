package QuizWebApp.AOP;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class PerformanceMonitoringAspect {

    @Around("QuizWebApp.AOP.PointCuts.inControllerLayer()")
    public Object logEndpointPerformance(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        Long startTime = System.currentTimeMillis();

        Object result = proceedingJoinPoint.proceed();

        Long duration = System.currentTimeMillis() - startTime;

        log.info("This endpoint took " + duration + " ms");

        return result;
    }

}
