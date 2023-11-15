package com.ruoyi.system.aspect;

import com.ruoyi.common.utils.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Aspect
@Component
public class CreateAspect {

    @Pointcut("@annotation(com.ruoyi.system.annotation.create)")
    public void pt(){}

    @Before("pt()")
    public  void createbefore(JoinPoint joinPoint){

        Object[] args=joinPoint.getArgs();
        if(args==null || args.length==0){
            return ;
        }
        Object entity = args[0];
        //创建时间和创建人
        LocalDateTime createTime = LocalDateTime.now();
        // 创建一个日期时间格式化器
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // 格式化 LocalDateTime 对象为字符串
        String formattedDateTime = createTime.format(formatter);
        String createBy= SecurityUtils.getUsername();
        Method setCreateTime = null;
        try {
            setCreateTime = entity.getClass().getDeclaredMethod("setCreateTime", String.class);
            Method setCreateBy = entity.getClass().getDeclaredMethod("setCreateBy", String.class);
            //通过反射为对象赋值
            setCreateTime.invoke(entity,formattedDateTime );
            setCreateBy.invoke(entity,createBy);

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

    }
}
