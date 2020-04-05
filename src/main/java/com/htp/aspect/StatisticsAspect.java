package com.htp.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class StatisticsAspect {
    /*private Map<Class<?>, Integer> counterReadAll = new HashMap<>();

    @Pointcut("execution(* *.readAll(..))")
    private void usersReadAllmethod() {
    }

    @AfterReturning("usersReadAllmethod()")
    public void count(JoinPoint jp) {
        Class<?> clazz = jp.getTarget().getClass();
        if (!counterReadAll.containsKey(clazz)) {
            counterReadAll.put(clazz, 0);
        }
        counterReadAll.put(clazz, counterReadAll.get(clazz) + 1);
    }
    public Map<Class<?>, Integer> getCounterReadAll() {
        return Collections.unmodifiableMap(counterReadAll);
    }*/

}
