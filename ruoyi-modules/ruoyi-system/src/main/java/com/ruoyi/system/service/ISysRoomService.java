package com.ruoyi.system.service;

import com.ruoyi.system.domain.SysRoom;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Du425
 * @since 2023-04-09
 */
public interface ISysRoomService extends IService<SysRoom> {

    List<SysRoom> findByType(String type);

}
