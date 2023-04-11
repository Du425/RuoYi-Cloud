package com.ruoyi.system.controller;

import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.system.domain.SysRoom;
import com.ruoyi.system.service.ISysRoomService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequiresPermissions("system:room:update")
    @Log(title = "房间管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult updateRooms(SysRoom room) {
        if (StringUtils.isEmpty((CharSequence) room.getId())) {
            return AjaxResult.error("房间不存在");
        }
        roomService.updateById(room.getId());
        return AjaxResult.success();
    }

    @RequiresPermissions("system:room:delete")
    @Log(title = "房间管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{id}")
    public AjaxResult deleteRoomById(@PathVariable String id) {
        roomService.removeById(id);
        return AjaxResult.success();
    }

    @RequiresPermissions("system:room:edit")
    @Log(title = "房间管理", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus() {
        return AjaxResult.success();
    }


}
