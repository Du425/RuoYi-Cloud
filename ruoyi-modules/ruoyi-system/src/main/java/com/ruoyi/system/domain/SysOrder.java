package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

import javax.validation.constraints.Pattern;

/**
 * 【请填写功能名称】对象 sys_order
 * 
 * @author ruoyi
 * @date 2023-04-12
 */
public class SysOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @Excel(name = "订单号")
    private Long id;

    /** $column.columnComment */
    @Excel(name = "房间ID")
    private Long roomId;

    /** $column.columnComment */
    @Excel(name = "用户ID")
    private Long userId;

    /** $column.columnComment */
    @Excel(name = "${comment}")
    private Long quantity;

    /** 订单状态 */
    @Excel(name = "订单状态")
    private String status;

    /** $column.columnComment */
    @Excel(name = "用户名")
    private String username;

    /** $column.columnComment */
    @Excel(name = "电话号码")
    @Pattern(regexp = "/^1(3\\d|4[5-9]|5[0-35-9]|6[567]|7[0-8]|8\\d|9[0-35-9])\\d{8}$/")
    private String phone;

    /** $column.columnComment */
    @Excel(name = "身份证")
    @Pattern(regexp = "/^\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x)$/")
    private String idCard;

    /** $column.columnComment */
    @Excel(name = "价格")
    private BigDecimal price;

    /** $column.columnComment */
    @Excel(name = "总价")
    private BigDecimal totalPrice;

    /** $column.columnComment */
    @Excel(name = "房间型号")
    private String roomCode;

    /** $column.columnComment */
    @Excel(name = "房间数量")
    private Long roomNumber;

    /** $column.columnComment */
    @Excel(name = "创建时间")
    private Date createTime;

    /** $column.columnComment */
    @Excel(name = "订单类型")
    private String orderType;

    /** $column.columnComment */
    @Excel(name = "订单类型ID")
    private Long roomTypeId;

    /** $column.columnComment */
    @Excel(name = "订单时长")
    private Long orderDays;

    @Excel(name = "入住日期")
    private LocalDate checkinDate;

    @Excel(name = "离店日期")
    private LocalDate checkoutDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public Long getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Long roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Long getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public Long getOrderDays() {
        return orderDays;
    }

    public void setOrderDays(Long orderDays) {
        this.orderDays = orderDays;
    }

    public LocalDate getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(LocalDate checkinDate) {
        this.checkinDate = checkinDate;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }
}
