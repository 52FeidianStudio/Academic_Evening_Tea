package com.ruoyi.system.mapper;


import com.ruoyi.system.domain.TblCreditUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TblCreditUserMapper {


    public int insertTblCreditUser(TblCreditUser tblCreditUser);
}
