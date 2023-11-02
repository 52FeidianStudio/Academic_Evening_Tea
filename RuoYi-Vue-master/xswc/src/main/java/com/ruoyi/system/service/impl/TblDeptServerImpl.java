package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.system.mapper.TblDeptMapper;
import com.ruoyi.system.service.TblDeptServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TblDeptServerImpl implements TblDeptServer {

    @Autowired
    private TblDeptMapper tblDeptMapper;
    @Override
    public List<SysDept> selectDeptList(SysDept dept) {
        return tblDeptMapper.selectDeptList(dept);
    }
}
