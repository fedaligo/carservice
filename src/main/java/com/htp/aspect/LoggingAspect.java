package com.htp.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
   /* private static final Logger LOGGER = Logger.getLogger(LoggingAspect.class);
    private Map<Class<?>, Integer> counter = new HashMap<>();

    //перед срезом (BEFORE)
    @Before("domainEntitiesMethods()")
    public void logBefore(JoinPoint joinPoint) {
        LOGGER.info("Method ---" + joinPoint.getSignature().getName() + "--- start");
    }


    //после успешного возврата (AFTER RETURNING)
    @AfterReturning(pointcut = "domainEntitiesMethods()")
    public void doAccessCheck(JoinPoint joinPoint) {
        LOGGER.info("Method ---" + joinPoint.getSignature().getName() + "--- finished");
    }
    // аспект в срезе кода(pointcut)
   *//* execution означает, что аспект выполняется только при
    запуске соответствующего метода doSmth. Звездочка перед путем
    означает, что возвращаемое значение может быть любое, а две точки в
    скобочках, что аргументы могут быть любые. Мы также можем указывать путь
    к интерфейсу, а не класса. Тогда аспект будет
    работать для всех классов, которые имплементируют данный интерфейс.*//*
    @Pointcut("execution(* com.htp.repository.*.*(..))")
    public void domainEntitiesMethods() {
    }

//полный контроль над ситуацией (AROUND) где самостоятельно надо вызывать исходный код в срезе
    @Around("domainEntitiesMethods()")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
        StopWatch sw = new StopWatch();
        sw.start();
        LOGGER.info("Going to call the method.");
        Object output = pjp.proceed();
        LOGGER.info("Method execution completed.");
        sw.stop();
        LOGGER.info("Method execution time: " + sw.getTotalTimeMillis() + " milliseconds.");
        return output;
    }*/
}
