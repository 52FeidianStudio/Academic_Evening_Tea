package com.ruoyi.system.mapper;


import com.ruoyi.system.domain.TblCreditUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TblCreditUserMapper {


    public int insertTblCreditUser(TblCreditUser tblCreditUser);

    public List<TblCreditUser> selectTblCreditUserList(TblCreditUser tblCreditUser);
}
