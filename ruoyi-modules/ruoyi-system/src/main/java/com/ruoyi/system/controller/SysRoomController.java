package com.ruoyi.system.controller;

import com.ruoyi.common.core.web.domain.AjaxResult;
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


    public AjaxResult bookRoom(HttpServletRequest request) {

        return AjaxResult.success();
    }

}
