
package com.example.bloodbank.network.models.newPassword;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewPassword {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private PasswordData data;
    @SerializedName("phone")
    @Expose
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public NewPassword(String phone) {
        this.phone = phone;
    }


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

    public PasswordData getData() {
        return data;
    }

    public void setData(PasswordData data) {
        this.data = data;
    }

}
