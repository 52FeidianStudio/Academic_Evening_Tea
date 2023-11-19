package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.DeptActivity;
import com.ruoyi.system.mapper.DeptActivityMapper;
import com.ruoyi.system.service.IDetpAcitivtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeptActivityServiceImpl implements IDetpAcitivtyService {

    @Autowired
    private DeptActivityMapper deptActivityMapper;
    @Override
    public void updateDeptActivity(DeptActivity deptActivity) {
        deptActivityMapper.updateDeptActivity(deptActivity);
        return ;
    }
}
