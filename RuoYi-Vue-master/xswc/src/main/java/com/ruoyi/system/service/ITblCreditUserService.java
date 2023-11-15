package com.ruoyi.system.service;

import com.ruoyi.system.domain.TblCreditUser;
import com.ruoyi.system.domain.TblFeedback;

import java.util.List;

public interface ITblCreditUserService {

    public List<TblCreditUser> selectTblCreditUserList(TblCreditUser tblCreditUser);
}
