package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysRoom;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Du425
 * @since 2023-04-09
 */
public interface SysRoomMapper extends BaseMapper<SysRoom> {

    List<SysRoom> findByType(String type);

}
