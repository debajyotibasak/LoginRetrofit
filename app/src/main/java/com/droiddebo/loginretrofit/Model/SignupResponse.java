package com.droiddebo.loginretrofit.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by DROID DEBO on 07-07-2017.
 */

public class SignupResponse {

    private int ReponseCode;

    private String ResponseMessagee;

    private int ResponseValue;

    public int getResponseCode() {
        return ReponseCode;
    }

    public void setResponseCode(int responseCode) {
        this.ReponseCode = responseCode;
    }

    public String getResponseMessage() {
        return ResponseMessagee;
    }

    public void setResponseMessage(String responseMessage) {
        this.ResponseMessagee = responseMessage;
    }

    public int getResponseValue() {
        return ResponseValue;
    }

    public void setResponseValue(int responseValue) {
        this.ResponseValue = responseValue;
    }
}
