package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.SysRoom;
import com.ruoyi.system.mapper.SysRoomMapper;
import com.ruoyi.system.service.ISysRoomService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Du425
 * @since 2023-04-09
 */
@Service
public class SysRoomServiceImpl extends ServiceImpl<SysRoomMapper, SysRoom> implements ISysRoomService {

    @Autowired
    SysRoomMapper sysRoomMapper;

    @Override
    public List<SysRoom> findByType(String type) {
        return null;
    }
}
