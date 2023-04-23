package com.ruoyi.system.controller;

import java.util.Date;
import java.util.List;
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
import com.ruoyi.system.domain.SysOrder;
import com.ruoyi.system.service.ISysOrderService;
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
@RequestMapping("/order")
public class SysOrderController extends BaseController
{
    @Autowired
    private ISysOrderService sysOrderService;

    /**
     * 查询【请填写功能名称】列表
     */
    @RequiresPermissions("system:order:list")
    @GetMapping("/list")
    public TableDataInfo list(SysOrder sysOrder)
    {
        startPage();
        List<SysOrder> list = sysOrderService.selectSysOrderList(sysOrder);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("system:order:export")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysOrder sysOrder)
    {
        List<SysOrder> list = sysOrderService.selectSysOrderList(sysOrder);
        ExcelUtil<SysOrder> util = new ExcelUtil<SysOrder>(SysOrder.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @RequiresPermissions("system:order:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysOrderService.selectSysOrderById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
    @RequiresPermissions("system:order:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysOrder sysOrder)
    {
        return toAjax(sysOrderService.insertSysOrder(sysOrder));
    }

    /**
     * 修改【请填写功能名称】
     */
    @RequiresPermissions("system:order:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysOrder sysOrder)
    {
        return toAjax(sysOrderService.updateSysOrder(sysOrder));
    }

    /**
     * 撤销订单【请填写功能名称】
     */
    @RequiresPermissions("system:order:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping("/withdraw")
    public AjaxResult withdraw(@RequestBody SysOrder sysOrder)
    {
        Date date = new Date();
        sysOrder.setUpdateTime(date);
        sysOrder.setStatus("取消订单");
        return toAjax(sysOrderService.updateSysOrder(sysOrder));
    }
    /**
     * 删除【请填写功能名称】
     */
    @RequiresPermissions("system:order:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysOrderService.deleteSysOrderByIds(ids));
    }

    @RequiresPermissions("system:order:finance")
    @GetMapping("/finance")
    public AjaxResult queryFinance() {
        List<SysOrder> sysOrders = sysOrderService.queryOrderFinance();
        return AjaxResult.success(sysOrders);
    }
}
