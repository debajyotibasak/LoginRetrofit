package com.droiddebo.loginretrofit.View;

import android.content.Intent;
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

import com.droiddebo.loginretrofit.Model.SignupResponse;
import com.droiddebo.loginretrofit.Model.SignupRequest;
import com.droiddebo.loginretrofit.Network.RequestInterface;
import com.droiddebo.loginretrofit.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    private AppCompatButton btn_register;
    private EditText et_phone, et_password, et_firstname, et_lastname;
    private TextView tv_login;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        btn_register = (AppCompatButton) findViewById(R.id.btn_register);
        tv_login = (TextView) findViewById(R.id.tv_login);
        et_firstname = (EditText) findViewById(R.id.et_firstname);
        et_lastname = (EditText) findViewById(R.id.et_lastname);
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_password = (EditText) findViewById(R.id.et_password);

        progress = (ProgressBar) findViewById(R.id.progress);

        btn_register.setOnClickListener(this);
        tv_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login:
                goToLogin();
                break;

            case R.id.btn_register:

                String firstname = et_firstname.getText().toString();
                String lastname = et_lastname.getText().toString();
                String phone = et_phone.getText().toString();
                String password = et_password.getText().toString();

                if (!firstname.isEmpty() && !lastname.isEmpty() && !phone.isEmpty() && !password.isEmpty()) {

                    progress.setVisibility(View.VISIBLE);
                    registerProcess(firstname, lastname, phone, password);

                } else {

                    Toast.makeText(this, "Fields are empty !", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    private void registerProcess(String firstname, String lastname, String phone, String password) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);

        /*User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);*/

        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setFirstname(firstname);
        signupRequest.setLastname(lastname);
        signupRequest.setMobileno(phone);
        signupRequest.setPassword(password);

        /*ServerRequest request = new ServerRequest();
        request.setOperation(Constants.REGISTER_OPERATION);
        request.setUser(user);*/

        Call<SignupResponse> response = requestInterface.signUp(signupRequest);

        response.enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(@NonNull Call<SignupResponse> call, @NonNull Response<SignupResponse> response) {

                if(response.isSuccessful()) {
                    SignupResponse resp = response.body();

                    String resValue = String.valueOf(resp.getResponseValue());

                    Toast.makeText(getApplicationContext(), "\nResponse Value: " + resValue, Toast.LENGTH_LONG).show();
                    progress.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(@NonNull Call<SignupResponse> call, @NonNull Throwable t) {

                progress.setVisibility(View.INVISIBLE);
                Log.d(Constants.TAG,"failed");
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }

    private void goToLogin() {
        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivity(loginIntent);
    }
}