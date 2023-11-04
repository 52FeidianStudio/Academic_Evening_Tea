package com.ruoyi.system.aspect;

import com.ruoyi.system.constant.ActivityConstant;
import com.ruoyi.system.domain.TblActivity;
import com.ruoyi.system.domain.vo.TblActivityVO;
import com.ruoyi.system.mapper.TblActivityMapper;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Aspect
@Component
public class UpdateActivityAspect {
    @Pointcut("@annotation(com.ruoyi.system.annotation.updateActivity)")
    public void pt(){}
    @Autowired
    private TblActivityMapper tblActivityMapper;

    @Before("pt()")
    public void updateActivity(){
        LocalDateTime now = LocalDateTime.now();
        TblActivity tblActivitynull= new TblActivity();
        List<TblActivity> tblActivitys= tblActivityMapper.selectTblActivityList(tblActivitynull);
        for(TblActivity tblActivity:tblActivitys){

            String time = tblActivity.getLat();
            //将时间转化成时间格式
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime startTime = LocalDateTime.parse(time, formatter);

            Duration duration = Duration.between(startTime, now);
            Duration twoHours = Duration.ofHours(2);

            if(duration.compareTo(twoHours) >= 0){
                tblActivity.setIsClose(ActivityConstant.ACTIVITYISCLOSE);
                if(startTime.isBefore(now)||startTime.isEqual(now)){
                    tblActivity.setIsEnd(ActivityConstant.ACTIVITYISEND);
                }
                tblActivityMapper.updateTblActivity(tblActivity);
            }


        }

    }
}
