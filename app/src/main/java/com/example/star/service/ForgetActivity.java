package com.example.star.service;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
                if (username.getText().toString().trim().length() <= 0) {
                    username.setError("Please Enter Your Username");
                    username.requestFocus();
                } else if (mobile.getText().toString().trim().length() <= 0) {
                    mobile.setError("Please Enter Your Password");
                    mobile.requestFocus();


                }
            }
        });


    }
}
