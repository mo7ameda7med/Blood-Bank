
package com.example.bloodbank.network.models.newPassword;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PasswordData {

    @SerializedName("pin_code_for_test")
    @Expose
    private Integer pinCodeForTest;
    @SerializedName("mail_fails")
    @Expose
    private List<Object> mailFails = null;
    @SerializedName("email")
    @Expose
    private String email;

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    @SerializedName("pin_code")
    @Expose
    private String pinCode;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("password_confirmation")
    @Expose
    private String passwordConfirmation;


    public Integer getPinCodeForTest() {
        return pinCodeForTest;
    }

    public void setPinCodeForTest(Integer pinCodeForTest) {
        this.pinCodeForTest = pinCodeForTest;
    }

    public List<Object> getMailFails() {
        return mailFails;
    }

    public void setMailFails(List<Object> mailFails) {
        this.mailFails = mailFails;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
