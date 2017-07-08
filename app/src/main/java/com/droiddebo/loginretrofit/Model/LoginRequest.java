package com.droiddebo.loginretrofit.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginRequest {


    private String mobileno;

    private String password;

    public String getMobile() {
        return mobileno;
    }

    public void setMobile(String mobile) {
        this.mobileno = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
