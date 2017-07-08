package com.droiddebo.loginretrofit.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by DROID DEBO on 07-07-2017.
 */

public class ResponseValue {

    private String TokenNo;

    private String AuthenticationStatus;

    private int ReferralId;

    private String MobileNo;

    private String Password;

    public String getTokenNo() {
        return TokenNo;
    }

    public void setTokenValue(String tokenValue) {
        this.TokenNo = tokenValue;
    }

    public String getAuthenticationStatus() {
        return AuthenticationStatus;
    }

    public void setAuth(String auth)
    {
        this.AuthenticationStatus = auth;
    }

    public int getReferralID() {
        return ReferralId;
    }

    public void setReferralID(int referralID) {
        this.ReferralId = referralID;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.MobileNo = mobileNo;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }
}
