package com.banking.sofware.design.fxtrading.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "Transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal id;

    @Column
    private String username;

    @Column
    private String primaryCcy;

    @Column
    private String secondaryCcy;

    @Column
    private BigDecimal rate;

    @Column
    private String action;

    @Column
    private BigDecimal notional;

    @Column
    private String tenor;

    @Column
    private Date date;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPrimaryCcy() {
        return primaryCcy;
    }

    public void setPrimaryCcy(String primaryCcy) {
        this.primaryCcy = primaryCcy;
    }

    public String getSecondaryCcy() {
        return secondaryCcy;
    }

    public void setSecondaryCcy(String secondaryCcy) {
        this.secondaryCcy = secondaryCcy;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public BigDecimal getNotional() {
        return notional;
    }

    public void setNotional(BigDecimal notional) {
        this.notional = notional;
    }

    public String getTenor() {
        return tenor;
    }

    public void setTenor(String tenor) {
        this.tenor = tenor;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
