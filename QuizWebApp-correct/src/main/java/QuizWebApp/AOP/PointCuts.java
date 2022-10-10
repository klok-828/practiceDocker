package QuizWebApp.AOP;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PointCuts {

    @Pointcut("within(QuizWebApp.controller.*)")
    public void inControllerLayer() {}
}
