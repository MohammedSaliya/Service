package com.example.star.service;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.star.service.APIClass.APIClient;
import com.example.star.service.Bean.Forgetpassword;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetActivity extends AppCompatActivity {

    TextView textView;
    EditText username, mobile, otpone, otptwo, otpthree, otpfour;
    Button req_otp;
    LinearLayout edt_otp;


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
        edt_otp = findViewById(R.id.edt_otp);
        textView = findViewById(R.id.textView);

        req_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = username.getText().toString().trim();
                String phone = mobile.getText().toString().trim();

                if (req_otp.getText().toString().equalsIgnoreCase("Request OTP")) {
                    if (username.getText().toString().trim().length() <= 0) {
                        username.setError("Please Enter Your Username");
                        username.requestFocus();
                    } else if (mobile.getText().toString().trim().length() <= 0) {
                        mobile.setError("Please Enter Your Password");
                        mobile.requestFocus();

                    } else {
                        getOTP(name, phone);
                        username.setVisibility(View.INVISIBLE);
                        mobile.setVisibility(View.INVISIBLE);
                        textView.setText("Pleace Enter Your OTP ");
                        edt_otp.setVisibility(View.VISIBLE);
                        req_otp.setText("Verify");

                    }

                } else if (req_otp.getText().toString().equalsIgnoreCase("verfiy")) {


                }

            }
        });


        otpone.addTextChangedListener(new

                                              TextWatcher() {
                                                  @Override
                                                  public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                                  }

                                                  @Override
                                                  public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                                      if (otpone.getText().toString().trim().length() == 1) {
                                                          otptwo.requestFocus();


                                                      }
                                                  }


                                                  @Override
                                                  public void afterTextChanged(Editable editable) {

                                                  }
                                              });
        otptwo.addTextChangedListener(new

                                              TextWatcher() {
                                                  @Override
                                                  public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                                  }

                                                  @Override
                                                  public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                                      if (otptwo.getText().toString().trim().length() == 1) {
                                                          otpthree.requestFocus();


                                                      }
                                                  }


                                                  @Override
                                                  public void afterTextChanged(Editable editable) {

                                                  }
                                              });
        otpthree.addTextChangedListener(new

                                                TextWatcher() {
                                                    @Override
                                                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                                    }

                                                    @Override
                                                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                                        if (otpthree.getText().toString().trim().length() == 1) {
                                                            otpfour.requestFocus();


                                                        }
                                                    }


                                                    @Override
                                                    public void afterTextChanged(Editable editable) {

                                                    }
                                                });


    }


    private void getOTP(String name, String phone) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", name);
            jsonObject.put("mobile", phone);
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


                Forgetpassword forgetpassword = response.body();
                Toast.makeText(ForgetActivity.this, forgetpassword.getMessage(), Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onFailure(Call<Forgetpassword> call, Throwable t) {
                Toast.makeText(ForgetActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}


