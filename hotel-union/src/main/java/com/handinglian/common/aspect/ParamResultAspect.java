package com.handinglian.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class ParamResultAspect {
    @Pointcut("execution(* com.handinglian.*.controller.*.*(..))")
    public void aspect() {
    }


    @Around("aspect()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        for (int i = 0;i < args.length;i++) {
            if (args[i] != null&&args[i] instanceof String) {
                boolean flag = ((String) args[i]).trim().length() == 0;
                if (flag){
                    args[i] = null;
                }
            }
        }

        return joinPoint.proceed(args);
    }

}

