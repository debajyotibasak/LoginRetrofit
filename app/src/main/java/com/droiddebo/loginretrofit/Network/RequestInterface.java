package com.droiddebo.loginretrofit.Network;

import com.droiddebo.loginretrofit.Model.LoginRequest;
import com.droiddebo.loginretrofit.Model.LoginResponse;
import com.droiddebo.loginretrofit.Model.SignupResponse;
import com.droiddebo.loginretrofit.Model.SignupRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RequestInterface {

    @POST("api/ReferralPartner/PostReferralPartner")
    Call<SignupResponse> signUp(@Body SignupRequest request);

    @POST("api/ReferralPartner/GetLogin")
    Call<LoginResponse> login(@Body LoginRequest request);

}

