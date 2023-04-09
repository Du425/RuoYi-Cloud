package com.ruoyi.system.controller;

import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.system.domain.SysRoom;
import com.ruoyi.system.service.ISysRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author DU425
 * @Date 2023/4/9 14:50
 * @Version 1.0
 * @Description 房间管理
 */
@RestController
@RequestMapping("/sysroom")
public class SysRoomController {

    @Autowired
    ISysRoomService roomService;

    @RequiresPermissions("system:room:add")
    @Log(title = "房间管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult insertRooms(SysRoom room) {
        boolean save = roomService.save(room);
        if (save) {
            return AjaxResult.success("保存成功");
        }
        return AjaxResult.error("保存失败，请重新上传");
    }


    public AjaxResult bookRoom(HttpServletRequest request) {

        return AjaxResult.success();
    }

}
