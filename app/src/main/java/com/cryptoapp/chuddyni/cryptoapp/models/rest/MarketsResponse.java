package com.cryptoapp.chuddyni.cryptoapp.models.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MarketsResponse {

    @JsonProperty("Data")
    private ExchangeResponseDataNode data;
    @JsonProperty("Response")
    private String response;

    public ExchangeResponseDataNode getData() {
        return data;
    }

}
