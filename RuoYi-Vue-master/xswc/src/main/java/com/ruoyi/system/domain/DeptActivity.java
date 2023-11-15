package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DeptActivity  {

    private  Long id;
    /**活动id*/
    private  Long activityId;
    //学院id
    private Long deptId;
    //限制人数
    private  Long maxNum;
    //剩余人数
    private  Long  resNum;
    //创建时间
    private String createTime;
    //更新时间
    private String updateTime;
    //创建人
    private  String createBy;
    //更新人
    private  String updateBy;



}
