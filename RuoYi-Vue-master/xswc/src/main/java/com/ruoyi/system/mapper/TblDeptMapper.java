package com.ruoyi.system.mapper;

import com.ruoyi.common.core.domain.entity.SysDept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TblDeptMapper {
    public List<SysDept> selectDeptList(SysDept dept);

}
