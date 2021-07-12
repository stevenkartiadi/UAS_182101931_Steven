package com.steven.uas_paml_steven_182101931.rest;

import com.steven.uas_paml_steven_182101931.model.User;
import com.steven.uas_paml_steven_182101931.model.GetUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("api/users")
    Call<GetUser> getUsersList();
}