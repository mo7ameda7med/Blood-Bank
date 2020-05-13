
package com.example.bloodbank.network.models.toggleFavourite;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ToggleFavourite {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private ToggleFavouriteData data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ToggleFavouriteData getData() {
        return data;
    }

    public void setData(ToggleFavouriteData data) {
        this.data = data;
    }

}
