package com.droiddebo.loginretrofit.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.droiddebo.loginretrofit.Model.LoginRequest;
import com.droiddebo.loginretrofit.Model.LoginResponse;
import com.droiddebo.loginretrofit.Network.RequestInterface;
import com.droiddebo.loginretrofit.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private AppCompatButton btn_login;
    private EditText et_phone, et_password;
    private TextView tv_register;
    private ProgressBar progress;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pref = getApplicationContext().getSharedPreferences("Shared Preferences", 0);

        btn_login = (AppCompatButton) findViewById(R.id.btn_login);
        tv_register = (TextView) findViewById(R.id.tv_register);
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_password = (EditText) findViewById(R.id.et_password);

        progress = (ProgressBar) findViewById(R.id.progress);

        btn_login.setOnClickListener(this);
        tv_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.tv_register:
                goToRegister();
                break;

            case R.id.btn_login:
                String phone = et_phone.getText().toString();
                String password = et_password.getText().toString();

                if(!phone.isEmpty() && !password.isEmpty()) {

                    progress.setVisibility(View.VISIBLE);
                    loginProcess(phone,password);

                } else {

                    Toast.makeText(this, "Fields are empty !", Toast.LENGTH_LONG).show();
                }
                break;

        }
    }

    private void loginProcess(String phone, String password) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);

        /*User user = new User();
        user.setEmail(email);
        user.setPassword(password);*/

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setMobile(phone);
        loginRequest.setPassword(password);

        /*ServerRequest request = new ServerRequest();
        request.setOperation(Constants.LOGIN_OPERATION);
        request.setUser(user);*/

        Call<LoginResponse> response = requestInterface.login(loginRequest);

        response.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {

                if(response.isSuccessful()) {
                    LoginResponse resp = response.body();

                    /*final String resCode = String.valueOf(resp.getResponseCode());*/
                    /*String resCode = String.valueOf(resp.getResponseCode());*/
                    /*System.out.print("RESPONSECODE" + resp.getResponseCode());*/


                    String referralID = String.valueOf(resp.getResponseValue().getReferralID());

                    Toast.makeText(getApplicationContext(), "Response Message: " + resp.getResponseMessage()
                                    + "\nAuthentication Status: " + resp.getResponseValue().getAuthenticationStatus()
                                    + "\nReferral ID: " + referralID,
                            Toast.LENGTH_LONG).show();

                    if (resp.getResponseMessage().equals("Success")) {
                        SharedPreferences.Editor editor = pref.edit();
                    /*SharedPreferences.Editor editor = pref.edit();*/
                        editor.putBoolean(Constants.IS_LOGGED_IN, true);
                        //TODO: get Name and email id too
                    /*editor.putString("Mobile No",resp.getResponseValue().getMobileNo());
                    editor.putString("Password",resp.getResponseValue().getPassword());*/

                        editor.putString("Referral ID", referralID);
                        editor.apply();
                        goToProfile();

                    }
                    progress.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {

                progress.setVisibility(View.INVISIBLE);
                Log.d(Constants.TAG,"failed");
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }

    private void goToRegister() {
        Intent intentRegister = new Intent(this, SignupActivity.class);
        startActivity(intentRegister);
    }

    private void goToProfile(){
        Intent intentProfile = new Intent(this, MainActivity.class);
        startActivity(intentProfile);
    }
    
}
