package com.ruoyi.system.domain.dto;

import com.ruoyi.system.domain.SysRoom;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author DU425
 * @Date 2023/4/13 21:16
 * @Version 1.0
 * @Description
 */

public class SysRoomDto extends SysRoom {

    private MultipartFile img;

    public MultipartFile getImg() {
        return img;
    }

    public void setImg(MultipartFile img) {
        this.img = img;
    }
}
