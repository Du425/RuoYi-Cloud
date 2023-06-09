package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysRecordMapper;
import com.ruoyi.system.domain.SysRecord;
import com.ruoyi.system.service.ISysRecordService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-04-12
 */
@Service
public class SysRecordServiceImpl implements ISysRecordService 
{
    @Autowired
    private SysRecordMapper sysRecordMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public SysRecord selectSysRecordById(Long id)
    {
        return sysRecordMapper.selectSysRecordById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param sysRecord 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<SysRecord> selectSysRecordList(SysRecord sysRecord)
    {
        return sysRecordMapper.selectSysRecordList(sysRecord);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param sysRecord 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertSysRecord(SysRecord sysRecord)
    {
        return sysRecordMapper.insertSysRecord(sysRecord);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param sysRecord 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateSysRecord(SysRecord sysRecord)
    {
        return sysRecordMapper.updateSysRecord(sysRecord);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteSysRecordByIds(Long[] ids)
    {
        return sysRecordMapper.deleteSysRecordByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteSysRecordById(Long id)
    {
        return sysRecordMapper.deleteSysRecordById(id);
    }
}
