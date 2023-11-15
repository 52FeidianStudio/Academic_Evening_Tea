package com.ruoyi.system.controller;

import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.TblCreditUser;
import com.ruoyi.system.service.ITblCreditUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.ruoyi.common.core.controller.BaseController;

@RestController
@RequestMapping("/system/credituser")
public class TblCreditUserController  extends BaseController{

@Autowired
private ITblCreditUserService tblCreditUserService;

    @GetMapping("/list")
    public TableDataInfo list(){
        startPage();
        Long userId = SecurityUtils.getUserId();
        TblCreditUser tblCreditUser = new TblCreditUser();
        tblCreditUser.setUserId(userId);
        List<TblCreditUser> tblCreditUsers = tblCreditUserService.selectTblCreditUserList(tblCreditUser);
        return getDataTable(tblCreditUsers);
    }
}
