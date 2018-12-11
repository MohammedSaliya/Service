package com.example.star.service;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.star.service.APIClass.APIClient;
import com.example.star.service.Bean.Forgetpassword;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetActivity extends AppCompatActivity {

    TextView textView;
    EditText username, mobile, otpone, otptwo, otpthree, otpfour;
    Button req_otp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);

        username = findViewById(R.id.usernmae);
        mobile = findViewById(R.id.mobile);
        otpone = findViewById(R.id.otpone);
        otptwo = findViewById(R.id.otptwo);
        otpthree = findViewById(R.id.otpthree);
        otpfour = findViewById(R.id.otpfour);
        req_otp = findViewById(R.id.req_otp);



        req_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = username.getText().toString().trim();
                String phone = mobile.getText().toString().trim();

                if (username.getText().toString().trim().length() <= 0) {
                    username.setError("Please Enter Your Username");
                    username.requestFocus();
                } else if (mobile.getText().toString().trim().length() <= 0) {
                    mobile.setError("Please Enter Your Password");
                    mobile.requestFocus();

                    getOTP(name, phone);
                }
            }
        });
    }


    private void getOTP(String name, String phone) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("mobile", phone);
            jsonObject.put("username", name);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Call<Forgetpassword> forgetpasswordCall = APIClient
                .getApiClient()
                .apInterface()
                .getOtp(jsonObject.toString());


        forgetpasswordCall.enqueue(new Callback<Forgetpassword>() {
            @Override
            public void onResponse(Call<Forgetpassword> call, Response<Forgetpassword> response) {

                if (response.code()==200)
                {
                    Forgetpassword forgetpassword = response.body();
                    Toast.makeText(ForgetActivity.this, forgetpassword.getMessage(), Toast.LENGTH_SHORT).show();
                }

                  else
                {
                    Toast.makeText(ForgetActivity.this, "Error :  " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Forgetpassword> call, Throwable t) {
                Toast.makeText(ForgetActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}
