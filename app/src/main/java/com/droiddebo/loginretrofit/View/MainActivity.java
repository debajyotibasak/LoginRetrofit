package com.droiddebo.loginretrofit.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.TextView;

import com.droiddebo.loginretrofit.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_mobileno, tv_password, tv_referralID;
    private SharedPreferences pref;
    private AppCompatButton btn_logout;
    private StringBuilder /*mobileNo, password,*/ referralID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pref = getApplicationContext().getSharedPreferences("Shared Preferences", 0);


        /*tv_mobileno = (TextView) findViewById(R.id.tv_mobileno);
        tv_password = (TextView) findViewById(R.id.tv_password);*/
        tv_referralID = (TextView) findViewById(R.id.tv_referralID);

        /*mobileNo = new StringBuilder("Mobile No : ");
        mobileNo.append(pref.getString(MOBILE, ""));

        password = new StringBuilder("Password : ");
        password.append(pref.getString(PASSWORD, ""));*/

        referralID = new StringBuilder("Referral ID : ");
        referralID.append(pref.getString("Referral ID", ""));

        /*tv_mobileno.setText(mobileNo);
        tv_password.setText(password);*/
        tv_referralID.setText(referralID);

        btn_logout = (AppCompatButton) findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            
            case R.id.btn_logout:
                logout();
                break;
        }
    }

    private void logout() {
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(Constants.IS_LOGGED_IN,false);
        /*editor.putString(MOBILE,"");
        editor.putString(PASSWORD,"");*/
        editor.putString("Referral ID","");
        editor.apply();
        goToLogin();
    }

    private void goToLogin() {
        Intent intentLogin = new Intent(this, LoginActivity.class);
        startActivity(intentLogin);
    }
}
