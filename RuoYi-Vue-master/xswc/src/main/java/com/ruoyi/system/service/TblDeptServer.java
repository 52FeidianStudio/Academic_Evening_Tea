package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.entity.SysDept;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TblDeptServer {
    List<SysDept> selectDeptList(SysDept dept);
}
