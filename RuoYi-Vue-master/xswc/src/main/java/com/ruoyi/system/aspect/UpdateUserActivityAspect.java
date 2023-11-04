package com.ruoyi.system.aspect;

import com.ruoyi.system.constant.ActivityConstant;
import com.ruoyi.system.domain.TblActivity;
import com.ruoyi.system.domain.TblUserActivity;
import com.ruoyi.system.mapper.TblActivityMapper;
import com.ruoyi.system.mapper.TblUserActivityMapper;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Aspect
@Component
public class UpdateUserActivityAspect {
    @Pointcut("@annotation(com.ruoyi.system.annotation.updateUserActivity)")
    public void pt(){}

    @Autowired
    private TblUserActivityMapper tblUserActivityMapper;
    @Autowired
    private TblActivityMapper tblActivityMapper;


    @Before("pt()")
    public void updateActivity(){
        TblUserActivity preTblUserActivity = new TblUserActivity();
        preTblUserActivity.setStatus(ActivityConstant.NOREGISTERED);
        List<TblUserActivity> tblUserActivities = tblUserActivityMapper.selectTblUserActivityList(preTblUserActivity);
        for(TblUserActivity tblUserActivity:tblUserActivities){
            Long isEnd = tblActivityMapper.getIsEndByActivityId(tblUserActivity.getActivityId());
            if(isEnd.equals(ActivityConstant.ACTIVITYISEND.longValue())){
                tblUserActivity.setStatus(ActivityConstant.NOSHOW);
                tblUserActivityMapper.updateTblUserActivity(tblUserActivity);
            }
        }
    }
}
