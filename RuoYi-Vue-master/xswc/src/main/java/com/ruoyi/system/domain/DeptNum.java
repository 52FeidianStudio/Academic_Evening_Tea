package com.ruoyi.system.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DeptNum {
    private Long deptId;
    private  Long maxNum;

    //创建时间
    private String createTime;
    //更新时间
    private String updateTime;
    //创建人
    private  String createBy;
    //更新人
    private  String updateBy;
}
