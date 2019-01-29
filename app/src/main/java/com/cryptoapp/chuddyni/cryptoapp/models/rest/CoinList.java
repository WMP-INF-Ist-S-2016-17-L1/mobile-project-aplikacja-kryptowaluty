package com.cryptoapp.chuddyni.cryptoapp.models.rest;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class CoinList {

    public static class Data {
        private List<DataNode> dataList;

        public List<DataNode> getDataList() {
            return dataList;
        }

        @JsonAnySetter
        public void setDataList(String key, DataNode dataNode) {
            if(dataList == null) dataList = new ArrayList<>();
            dataList.add(dataNode);
        }
    }

    @JsonProperty("Response")
    private String Response;
    @JsonProperty("Message")
    private String Message;
    @JsonProperty("BaseImageUrl")
    private String BaseImageUrl;
    @JsonProperty("BaseLinkUrl")
    private String BaseLinkUrl;
    @JsonProperty("DefaultWatchlist")
    private WatchList DefaultWatchlist;
    @JsonProperty("Data")
    private Data data;
    @JsonProperty("Type")
    private int Type;

    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        Response = response;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getBaseImageUrl() {
        return BaseImageUrl;
    }

    public void setBaseImageUrl(String baseImageUrl) {
        BaseImageUrl = baseImageUrl;
    }

    public String getBaseLinkUrl() {
        return BaseLinkUrl;
    }

    public void setBaseLinkUrl(String baseLinkUrl) {
        BaseLinkUrl = baseLinkUrl;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public WatchList getDefaultWatchlist() {
        return DefaultWatchlist;
    }

    public void setDefaultWatchlist(WatchList defaultWatchlist) {
        DefaultWatchlist = defaultWatchlist;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}




