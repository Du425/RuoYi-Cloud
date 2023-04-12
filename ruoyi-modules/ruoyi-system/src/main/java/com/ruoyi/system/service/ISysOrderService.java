package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysOrder;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2023-04-12
 */
public interface ISysOrderService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public SysOrder selectSysOrderById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param sysOrder 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<SysOrder> selectSysOrderList(SysOrder sysOrder);

    /**
     * 新增【请填写功能名称】
     * 
     * @param sysOrder 【请填写功能名称】
     * @return 结果
     */
    public int insertSysOrder(SysOrder sysOrder);

    /**
     * 修改【请填写功能名称】
     * 
     * @param sysOrder 【请填写功能名称】
     * @return 结果
     */
    public int updateSysOrder(SysOrder sysOrder);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteSysOrderByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteSysOrderById(Long id);
}
