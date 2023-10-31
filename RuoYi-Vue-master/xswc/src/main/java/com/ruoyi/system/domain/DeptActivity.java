package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DeptActivity extends BaseEntity {

    private  Long id;
    /**活动id*/
    private  Long activityId;
    //学院id
    private Long deptId;
    //限制人数
    private  Long maxNum;
    //剩余人数
    private  Long  resNum;

}
