package com.ruoyi.system.mapper;


import com.ruoyi.system.annotation.create;
import com.ruoyi.system.domain.TblGoods;
import com.ruoyi.system.domain.TblLike;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TblLikeMapper {

//    @create
    public int insertTblLike(TblLike tblLike);


    public TblLike selectTblLike(TblLike tblLike);
    public int deleteTblLike(TblLike tblLike);

    List<TblLike> selectTblLikeList(TblLike tblLike);

//    public int deleteTblLikeByIds(Long[] ids);
}
