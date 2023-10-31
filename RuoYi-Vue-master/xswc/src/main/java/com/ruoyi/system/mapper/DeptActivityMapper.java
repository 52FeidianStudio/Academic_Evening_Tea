package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.DeptActivity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeptActivityMapper {

    /**
     * 插入一条活动的学院人数限制
     * @param deptActivity
     * @return
     */
    public int insertDeptActivity(DeptActivity deptActivity);

    Long getResNum(DeptActivity deptActivity);

    void updateDeptActivity(DeptActivity deptActivity);
}
