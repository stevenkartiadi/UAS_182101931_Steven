package com.steven.uas_paml_steven_182101931;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.steven.uas_paml_steven_182101931.model.User;
import com.steven.uas_paml_steven_182101931.model.GetUser;
import com.steven.uas_paml_steven_182101931.rest.ApiInterface;
import com.steven.uas_paml_steven_182101931.rest.ApiClient;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private UsersAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("loading");
        progressDialog.show();

        ApiInterface service = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<GetUser> call = service.getUsersList();

        call.enqueue(new Callback<GetUser>() {
            @Override
            public void onResponse(Call<GetUser> call, Response<GetUser> response) {
                progressDialog.dismiss();
                List<User> userList = response.body().getData();
                generateDataList(userList);
            }

            @Override
            public void onFailure(Call<GetUser> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

    private void generateDataList(List<User> userList){
        recyclerView = findViewById(R.id.CsRecyclerView);
        adapter      = new UsersAdapter(this, userList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}