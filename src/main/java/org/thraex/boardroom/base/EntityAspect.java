package org.thraex.boardroom.base;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author 鬼王
 * @date 2021/08/24 17:04
 */
@Aspect
@Component
public class EntityAspect {

    @Pointcut("execution(* org.thraex.toolkit.jpa.service.*.save*(..))")
    public void points() {}

    @Before("points()")
    public void before(JoinPoint point) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println(point);
        Object[] args = point.getArgs();
        Object arg = args[0];
        System.out.println(arg);
        Class<?> aClass = arg.getClass();
        Method getId = aClass.getMethod("getId");
        Object id = getId.invoke(arg);
        if (Objects.isNull(id)) {
            Method setCreateBy = aClass.getMethod("setCreateBy", String.class);
            Method setCreateTime = aClass.getMethod("setCreateTime", LocalDateTime.class);
            setCreateBy.invoke(arg, "HANZO");
            setCreateTime.invoke(arg, LocalDateTime.now());
        } else {
            Method setUpdateBy = aClass.getMethod("setUpdateBy", String.class);
            Method setUpdateTime = aClass.getMethod("setUpdateTime", LocalDateTime.class);
        }
    }

}
