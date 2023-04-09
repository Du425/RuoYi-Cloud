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
@TableName("sys_order")
@ApiModel(value = "SysOrder对象", description = "")
public class SysOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer roomId;

    private Integer userId;

    private Integer quantity;

    @ApiModelProperty("订单状态")
    private String status;

    private String username;

    private String phone;

    private String idCard;

    private BigDecimal price;

    private BigDecimal totalPrice;

    private String roomTile;

    private Integer roomNumber;

    private LocalDateTime creatTime;

    private LocalDateTime updateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
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

    public String getRoomTile() {
        return roomTile;
    }

    public void setRoomTile(String roomTile) {
        this.roomTile = roomTile;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public LocalDateTime getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(LocalDateTime creatTime) {
        this.creatTime = creatTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "SysOrder{" +
        "id=" + id +
        ", roomId=" + roomId +
        ", userId=" + userId +
        ", quantity=" + quantity +
        ", status=" + status +
        ", username=" + username +
        ", phone=" + phone +
        ", idCard=" + idCard +
        ", price=" + price +
        ", totalPrice=" + totalPrice +
        ", roomTile=" + roomTile +
        ", roomNumber=" + roomNumber +
        ", creatTime=" + creatTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
