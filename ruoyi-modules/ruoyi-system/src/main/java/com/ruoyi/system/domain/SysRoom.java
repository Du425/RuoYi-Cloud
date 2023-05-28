package com.ruoyi.system.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

import javax.validation.constraints.Pattern;

/**
 * 【请填写功能名称】对象 sys_room
 * 
 * @author ruoyi
 * @date 2023-04-12
 */
public class SysRoom extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @Excel(name = "房间ID")
    private Integer id;

    /** $column.columnComment */
    @Excel(name = "单价")
    private BigDecimal price;

    /** 房型 */
    @Excel(name = "房型")
    private String code;

    /** $column.columnComment */
    @Excel(name = "标题")
    private String title;

    /** 房间数量 */
    @Excel(name = "房间数量")
    private Long number;

    /** $column.columnComment */
    @Excel(name = "图片URL")
    private String imgUrl;

    /** 在售、停售 */
    @Excel(name = "在售、停售")
    private String roomStatus;

    /** $column.columnComment */
    @Excel(name = "房间类型")
    private String roomType;

    @Pattern(regexp = "^.{6,}$")
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public BigDecimal getPrice()
    {
        return price;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setNumber(Long number) 
    {
        this.number = number;
    }

    public Long getNumber() 
    {
        return number;
    }
    public void setImgUrl(String imgUrl) 
    {
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() 
    {
        return imgUrl;
    }
    public void setRoomStatus(String roomStatus) 
    {
        this.roomStatus = roomStatus;
    }

    public String getRoomStatus() 
    {
        return roomStatus;
    }
    public void setRoomType(String roomType) 
    {
        this.roomType = roomType;
    }

    public String getRoomType() 
    {
        return roomType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("price", getPrice())
            .append("code", getCode())
            .append("title", getTitle())
            .append("number", getNumber())
            .append("imgUrl", getImgUrl())
            .append("roomStatus", getRoomStatus())
            .append("createTime", getCreateTime())
            .append("roomType", getRoomType())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
