package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysRoom;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2023-04-12
 */
public interface ISysRoomService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public SysRoom selectSysRoomById(Integer id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param sysRoom 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<SysRoom> selectSysRoomList(SysRoom sysRoom);

    /**
     * 新增【请填写功能名称】
     * 
     * @param sysRoom 【请填写功能名称】
     * @return 结果
     */
    public int insertSysRoom(SysRoom sysRoom);

    /**
     * 修改【请填写功能名称】
     * 
     * @param sysRoom 【请填写功能名称】
     * @return 结果
     */
    public int updateSysRoom(SysRoom sysRoom);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteSysRoomByIds(String[] ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteSysRoomById(String id);
}
