package com.ruoyi.system.controller;


import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.system.domain.SysRecord;
import com.ruoyi.system.service.ISysRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Du425
 * @since 2023-04-09
 * @Description 记录订单操作
 */
@Controller
@RequestMapping("/sysRecord")
public class SysRecordController {

    @Autowired
    ISysRecordService recordService;

    /**
     * 订单状态
     * @param record
     * @return
     */
    @RequiresPermissions("system:record:insert")
    @Log(title = "订单管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insertRecord(SysRecord record) {

        return AjaxResult.success();
    }


}

