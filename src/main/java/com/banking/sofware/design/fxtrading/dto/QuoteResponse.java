package com.banking.sofware.design.fxtrading.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QuoteResponse {

    private BigDecimal buyRate;
    private BigDecimal sellRate;

    public QuoteResponse() {

    }

    public QuoteResponse(BigDecimal buyRate, BigDecimal sellRate) {
        this.buyRate = buyRate;
        this.sellRate = sellRate;
    }

    public BigDecimal getBuyRate() {
        return buyRate;
    }

    public void setBuyRate(BigDecimal buyRate) {
        this.buyRate = buyRate;
    }

    public BigDecimal getSellRate() {
        return sellRate;
    }

    public void setSellRate(BigDecimal sellRate) {
        this.sellRate = sellRate;
    }
}
