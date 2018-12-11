package com.example.star.service.APIClass;

import com.example.star.service.Bean.Forgetpassword;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APInterface {

    @Headers("Content-Type: application/json")
    @POST("service_engineer_forgotpassword")
    Call<Forgetpassword> getOtp(@Body String post);


}


