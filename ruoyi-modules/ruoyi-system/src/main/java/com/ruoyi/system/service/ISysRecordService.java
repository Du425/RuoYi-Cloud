package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysRecord;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2023-04-12
 */
public interface ISysRecordService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public SysRecord selectSysRecordById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param sysRecord 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<SysRecord> selectSysRecordList(SysRecord sysRecord);

    /**
     * 新增【请填写功能名称】
     * 
     * @param sysRecord 【请填写功能名称】
     * @return 结果
     */
    public int insertSysRecord(SysRecord sysRecord);

    /**
     * 修改【请填写功能名称】
     * 
     * @param sysRecord 【请填写功能名称】
     * @return 结果
     */
    public int updateSysRecord(SysRecord sysRecord);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteSysRecordByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteSysRecordById(Long id);
}
