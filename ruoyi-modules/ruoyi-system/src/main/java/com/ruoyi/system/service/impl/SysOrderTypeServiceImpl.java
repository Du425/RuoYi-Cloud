package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysOrderTypeMapper;
import com.ruoyi.system.domain.SysOrderType;
import com.ruoyi.system.service.ISysOrderTypeService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-04-12
 */
@Service
public class SysOrderTypeServiceImpl implements ISysOrderTypeService 
{
    @Autowired
    private SysOrderTypeMapper sysOrderTypeMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public SysOrderType selectSysOrderTypeById(Long id)
    {
        return sysOrderTypeMapper.selectSysOrderTypeById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param sysOrderType 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<SysOrderType> selectSysOrderTypeList(SysOrderType sysOrderType)
    {
        return sysOrderTypeMapper.selectSysOrderTypeList(sysOrderType);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param sysOrderType 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertSysOrderType(SysOrderType sysOrderType)
    {
        sysOrderType.setCreateTime(DateUtils.getNowDate());
        return sysOrderTypeMapper.insertSysOrderType(sysOrderType);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param sysOrderType 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateSysOrderType(SysOrderType sysOrderType)
    {
        sysOrderType.setUpdateTime(DateUtils.getNowDate());
        return sysOrderTypeMapper.updateSysOrderType(sysOrderType);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteSysOrderTypeByIds(Long[] ids)
    {
        return sysOrderTypeMapper.deleteSysOrderTypeByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteSysOrderTypeById(Long id)
    {
        return sysOrderTypeMapper.deleteSysOrderTypeById(id);
    }
}
