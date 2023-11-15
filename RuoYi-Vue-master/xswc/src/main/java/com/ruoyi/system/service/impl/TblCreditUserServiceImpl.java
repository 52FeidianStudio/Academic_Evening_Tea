package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.TblCreditUser;
import com.ruoyi.system.mapper.TblCreditUserMapper;
import com.ruoyi.system.service.ITblCreditUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TblCreditUserServiceImpl implements ITblCreditUserService {

    @Autowired
    private TblCreditUserMapper tblCreditUserMapper;
    @Override
    public List<TblCreditUser> selectTblCreditUserList(TblCreditUser tblCreditUser) {
        List<TblCreditUser> tblCreditUsers=   tblCreditUserMapper.selectTblCreditUserList(tblCreditUser);
        return tblCreditUsers;
    }
}
