package com.ticketsystem.model;

import java.math.BigDecimal;
import java.util.Date;

public class OrderForm {
    private Integer orderFormId;

    private Integer userId;

    private BigDecimal ticketNumber;

    private Double totalPrice;

    private Date orderTime;

    public Integer getOrderFormId() {
        return orderFormId;
    }

    public void setOrderFormId(Integer orderFormId) {
        this.orderFormId = orderFormId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(BigDecimal ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }
}