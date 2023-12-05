package com.ruoyi.system.aspect;

import com.ruoyi.common.utils.StringUtils;
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
    // 在指定切点(pt)之前执行的方法
    @Before("pt()")
    public void updateActivity(){
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();

        // 创建一个空的TblActivity对象
        TblActivity tblActivitynull= new TblActivity();

        // 查询所有TblActivity记录
        List<TblActivity> tblActivitys= tblActivityMapper.selectTblActivityList(tblActivitynull);

        // 遍历所有TblActivity记录
        for(TblActivity tblActivity:tblActivitys){
            // 获取活动开始时间
            String time = tblActivity.getLat();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            String avalibableTimeStr = tblActivity.getStartTime();//获取可以报名的时间
            LocalDateTime avalibableTime = StringUtils.isNull(avalibableTimeStr)?
                    LocalDateTime.parse("1999-12-12 00:00:00", formatter)://未来时间不可能到达
                    LocalDateTime.parse(avalibableTimeStr, formatter);

            Duration begainEntoll = Duration.between(avalibableTime, now);

            // 将时间字符串转化成时间格式
            LocalDateTime startTime = LocalDateTime.parse(time, formatter);


            // 计算活动开始时间与当前时间的差距
            Duration duration = Duration.between(startTime, now);

            // 定义一个两小时的时长
            Duration twoHours = Duration.ofHours(2);

            //以下为关闭活动操作
            // 如果活动持续时间超过两小时


            if(begainEntoll.compareTo(Duration.ofHours(0)) < 0){
                tblActivity.setIsClose(0);
                tblActivityMapper.updateTblActivity(tblActivity);
                continue;
            }else{
                Integer isClose = tblActivity.getIsClose();
                if(isClose == 0) {
                    tblActivity.setIsClose(1);
                    tblActivityMapper.updateTblActivity(tblActivity);
                }
            }
            if(duration.compareTo(twoHours) >= 0){
                // 设置活动为关闭状态
                tblActivity.setIsClose(ActivityConstant.ACTIVITYISCLOSE);

                // 如果活动开始时间早于或等于当前时间，设置活动为已结束状态
                if(startTime.isBefore(now) || startTime.isEqual(now)){
                    tblActivity.setIsEnd(ActivityConstant.ACTIVITYISEND);
                }

                // 更新TblActivity记录
                tblActivityMapper.updateTblActivity(tblActivity);
            }
            /*
            * 规则：
            * 如果活动开始后时间超过两小时，则表明活动已经关闭了
            * 如果还没到活动开始时间则优先设置为未开始
            * */
        }
    }
}
