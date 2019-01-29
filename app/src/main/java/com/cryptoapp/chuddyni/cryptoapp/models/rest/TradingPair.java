package com.cryptoapp.chuddyni.cryptoapp.models.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TradingPair {

    @JsonProperty("Data")
    private List<TradingPairNode> data;
    @JsonProperty("Response")
    private String response;

    public List<TradingPairNode> getData() {
        return data;
    }

    public String getResponse() {
        return response;
    }

    public void setData(List<TradingPairNode> data) {
        this.data = data;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
