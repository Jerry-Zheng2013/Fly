package com.ticketsystem.model;

import java.math.BigDecimal;
import java.util.Date;

public class Flight {
    private Integer flightId;

    private Date startTime;

    private String startCity;

    private String endCity;

    private BigDecimal peopleNumber;

    private BigDecimal leftTicket;

    private Double ticketPrice;

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getStartCity() {
        return startCity;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity == null ? null : startCity.trim();
    }

    public String getEndCity() {
        return endCity;
    }

    public void setEndCity(String endCity) {
        this.endCity = endCity == null ? null : endCity.trim();
    }

    public BigDecimal getPeopleNumber() {
        return peopleNumber;
    }

    public void setPeopleNumber(BigDecimal peopleNumber) {
        this.peopleNumber = peopleNumber;
    }

    public BigDecimal getLeftTicket() {
        return leftTicket;
    }

    public void setLeftTicket(BigDecimal leftTicket) {
        this.leftTicket = leftTicket;
    }

    public Double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}