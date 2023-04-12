package com.ruoyi.system.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.system.domain.SysOrderType;
import com.ruoyi.system.service.ISysOrderTypeService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2023-04-12
 */
@RestController
@RequestMapping("/type")
public class SysOrderTypeController extends BaseController
{
    @Autowired
    private ISysOrderTypeService sysOrderTypeService;

    /**
     * 查询【请填写功能名称】列表
     */
    @RequiresPermissions("system:type:list")
    @GetMapping("/list")
    public TableDataInfo list(SysOrderType sysOrderType)
    {
        startPage();
        List<SysOrderType> list = sysOrderTypeService.selectSysOrderTypeList(sysOrderType);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("system:type:export")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysOrderType sysOrderType)
    {
        List<SysOrderType> list = sysOrderTypeService.selectSysOrderTypeList(sysOrderType);
        ExcelUtil<SysOrderType> util = new ExcelUtil<SysOrderType>(SysOrderType.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @RequiresPermissions("system:type:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysOrderTypeService.selectSysOrderTypeById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
    @RequiresPermissions("system:type:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysOrderType sysOrderType)
    {
        return toAjax(sysOrderTypeService.insertSysOrderType(sysOrderType));
    }

    /**
     * 修改【请填写功能名称】
     */
    @RequiresPermissions("system:type:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysOrderType sysOrderType)
    {
        return toAjax(sysOrderTypeService.updateSysOrderType(sysOrderType));
    }

    /**
     * 删除【请填写功能名称】
     */
    @RequiresPermissions("system:type:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysOrderTypeService.deleteSysOrderTypeByIds(ids));
    }
}
