package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author Du425
 * @since 2023-04-09
 */
@TableName("sys_room")
@ApiModel(value = "SysRoom对象", description = "")
public class SysRoom implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private BigDecimal price;

    private String desc;

    @ApiModelProperty("房型")
    private String code;

    private String title;

    @ApiModelProperty("房间数量")
    private Integer number;

    private String imgUrl;

    @ApiModelProperty("在售、停售")
    private String roomStatus;

    private LocalDateTime createTime;

    private String roomType;

    private LocalDateTime updateTime;

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SysRoom{" +
                "id='" + id + '\'' +
                ", price=" + price +
                ", desc='" + desc + '\'' +
                ", code='" + code + '\'' +
                ", title='" + title + '\'' +
                ", number=" + number +
                ", imgUrl='" + imgUrl + '\'' +
                ", roomStatus='" + roomStatus + '\'' +
                ", createTime=" + createTime +
                ", roomType='" + roomType + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
