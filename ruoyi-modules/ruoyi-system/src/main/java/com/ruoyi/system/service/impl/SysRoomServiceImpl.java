package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysRoomMapper;
import com.ruoyi.system.domain.SysRoom;
import com.ruoyi.system.service.ISysRoomService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-04-12
 */
@Service
public class SysRoomServiceImpl implements ISysRoomService 
{
    @Autowired
    private SysRoomMapper sysRoomMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public SysRoom selectSysRoomById(Integer id)
    {
        return sysRoomMapper.selectSysRoomById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param sysRoom 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<SysRoom> selectSysRoomList(SysRoom sysRoom)
    {
        return sysRoomMapper.selectSysRoomList(sysRoom);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param sysRoom 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertSysRoom(SysRoom sysRoom)
    {
        sysRoom.setCreateTime(DateUtils.getNowDate());
        return sysRoomMapper.insertSysRoom(sysRoom);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param sysRoom 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateSysRoom(SysRoom sysRoom)
    {
        sysRoom.setUpdateTime(DateUtils.getNowDate());
        return sysRoomMapper.updateSysRoom(sysRoom);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteSysRoomByIds(String[] ids)
    {
        return sysRoomMapper.deleteSysRoomByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteSysRoomById(String id)
    {
        return sysRoomMapper.deleteSysRoomById(id);
    }
}
