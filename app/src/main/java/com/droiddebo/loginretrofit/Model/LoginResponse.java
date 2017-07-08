package com.droiddebo.loginretrofit.Model;


import android.widget.Toast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    private int ResponseCode;

    private String ResponseMessage;

    private ResponseValue ResponseValue;

    public int getResponseCode()
    {
        return ResponseCode;
    }

    public void setResponseCode(int responseCode)
    {
        this.ResponseCode = responseCode;
    }

    public String getResponseMessage() {
        return ResponseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.ResponseMessage = responseMessage;
    }

    public ResponseValue getResponseValue() {
        return ResponseValue;
    }

    public void setResponseValue(ResponseValue responseValue) {
        this.ResponseValue = responseValue;
    }

}
