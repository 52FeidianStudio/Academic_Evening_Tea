package com.ruoyi.system.aspect;

import com.ruoyi.common.utils.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Aspect
@Component
public class UpdateAspect {

    @Pointcut("@annotation(com.ruoyi.system.annotation.update)")
    public void pt(){}


    @Before("pt()")
    public  void createbefore(JoinPoint joinPoint){
        MethodSignature signatur= (MethodSignature) joinPoint.getSignature();
        Object[] args=joinPoint.getArgs();
        if(args==null || args.length==0){
            return ;
        }
        Object entity = args[0];
        //修改时间和修改人
        LocalDateTime updateTime = LocalDateTime.now();
        String updateBy= SecurityUtils.getUsername();
        Method setCreateTime = null;
        try {
            Method   setUpdateTime = entity.getClass().getDeclaredMethod("setUpdateTime", LocalDateTime.class);
            Method setUpdateBy = entity.getClass().getDeclaredMethod("setUpdateBy", String.class);
            //通过反射为对象赋值
            setUpdateTime.invoke(entity,updateTime );
            setUpdateBy.invoke(entity,updateBy);

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

    }
}
