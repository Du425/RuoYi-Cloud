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
import com.ruoyi.system.domain.SysRecord;
import com.ruoyi.system.service.ISysRecordService;
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
@RequestMapping("/record")
public class SysRecordController extends BaseController
{
    @Autowired
    private ISysRecordService sysRecordService;

    /**
     * 查询【请填写功能名称】列表
     */
    @RequiresPermissions("system:record:list")
    @GetMapping("/list")
    public TableDataInfo list(SysRecord sysRecord)
    {
        startPage();
        List<SysRecord> list = sysRecordService.selectSysRecordList(sysRecord);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("system:record:export")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysRecord sysRecord)
    {
        List<SysRecord> list = sysRecordService.selectSysRecordList(sysRecord);
        ExcelUtil<SysRecord> util = new ExcelUtil<SysRecord>(SysRecord.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @RequiresPermissions("system:record:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysRecordService.selectSysRecordById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
    @RequiresPermissions("system:record:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysRecord sysRecord)
    {
        return toAjax(sysRecordService.insertSysRecord(sysRecord));
    }

    /**
     * 修改【请填写功能名称】
     */
    @RequiresPermissions("system:record:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysRecord sysRecord)
    {
        return toAjax(sysRecordService.updateSysRecord(sysRecord));
    }

    /**
     * 删除【请填写功能名称】
     */
    @RequiresPermissions("system:record:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysRecordService.deleteSysRecordByIds(ids));
    }
}
