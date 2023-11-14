package com.ruoyi.system.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.system.service.TblDeptServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static com.ruoyi.common.core.domain.AjaxResult.success;

@RestController
@RequestMapping("/system/depts")
public class TblDeptController {

    @Autowired
    private  TblDeptServer tblDeptServer;

    @GetMapping("/list")
//    @PreAuthorize("@ss.hasPermi('system:depts:list')")
    public AjaxResult list(SysDept dept)
    {
        List<SysDept> depts = new ArrayList<>();
        String[] departmentNames = {
                "园艺林学学院",
                "食品科学技术学院",
                "生命科学技术学院",
                "外国语学院",
                "信息学院",
                "化学学院",
                "动物科学技术学院、动物医学院",
                "资源与环境学院",
                "经济管理学院",
                "植物科学技术学院",
                "文法学院",
                "马克思主义学院",
                "工学院",
                "生物医学与健康学院",
                "公共管理学院",
                "水产学院",
                "体育部",
                "本科生院（教务处、党委学生工作部、党委武装部）"
        };

        // 创建部门对象并设置部门名字
        SysDept[] departments = new SysDept[18];
        for (int i = 0; i < 18; i++) {
            departments[i] = new SysDept();
            departments[i].setDeptName(departmentNames[i]);
            depts.add(departments[i]);
        }
        return success(depts);
    }


}
