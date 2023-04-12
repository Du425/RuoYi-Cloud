package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysOrderMapper;
import com.ruoyi.system.domain.SysOrder;
import com.ruoyi.system.service.ISysOrderService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-04-12
 */
@Service
public class SysOrderServiceImpl implements ISysOrderService 
{
    @Autowired
    private SysOrderMapper sysOrderMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public SysOrder selectSysOrderById(Long id)
    {
        return sysOrderMapper.selectSysOrderById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param sysOrder 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<SysOrder> selectSysOrderList(SysOrder sysOrder)
    {
        return sysOrderMapper.selectSysOrderList(sysOrder);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param sysOrder 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertSysOrder(SysOrder sysOrder)
    {
        return sysOrderMapper.insertSysOrder(sysOrder);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param sysOrder 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateSysOrder(SysOrder sysOrder)
    {
        sysOrder.setUpdateTime(DateUtils.getNowDate());
        return sysOrderMapper.updateSysOrder(sysOrder);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteSysOrderByIds(Long[] ids)
    {
        return sysOrderMapper.deleteSysOrderByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteSysOrderById(Long id)
    {
        return sysOrderMapper.deleteSysOrderById(id);
    }
}
