package QuizWebApp.AOP;

import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

//    private Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
//
//    @Before("QuizWebApp.AOP.PointCuts.inControllerLayer()")
//    public void logStartTime() {
//        logger.info("From LoggingAspect.logStartTime in controller: " + System.currentTimeMillis());
//    }
//
//    @After("QuizWebApp.AOP.PointCuts.inControllerLayer()")
//    public void logEndTime(JoinPoint joinPoint) {
//        logger.info("From LoggingAspect.logEndTime in service: " + System.currentTimeMillis()  + ": " + joinPoint.getSignature());
//    }
}
