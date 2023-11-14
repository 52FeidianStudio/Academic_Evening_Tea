package com.ruoyi.system.mapper;

import com.ruoyi.system.annotation.create;
import com.ruoyi.system.domain.DeptActivity;
import com.ruoyi.system.domain.TblActivity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import java.util.List;

@Mapper
public interface DeptActivityMapper {

    /**
     * 插入一条活动的学院人数限制
     * @param deptActivity
     * @return
     */

    @create
    public int insertDeptActivity(DeptActivity deptActivity);

    Long getResNum(DeptActivity deptActivity);

    void updateDeptActivity(DeptActivity deptActivity);

    /**
     * 用户查询活动学院限制集合
     */

    public List<DeptActivity> selectDeptActivityList(DeptActivity deptActivity);
}
