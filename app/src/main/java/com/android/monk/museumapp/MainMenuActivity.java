package com.android.monk.museumapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.android.monk.museumapp.API.MuseumAPI;
import com.android.monk.museumapp.model.DataWilayah;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by herisulistiyanto on 8/20/16.
 */
public class MainMenuActivity extends AppCompatActivity implements Callback<DataWilayah> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);
        initRetrofit();
    }

    private void initRetrofit() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MuseumAPI.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        MuseumAPI museumAPI = retrofit.create(MuseumAPI.class);

        Call<DataWilayah> call = museumAPI.getDataProp();

        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<DataWilayah> call, Response<DataWilayah> response) {
        Toast.makeText(this, "success", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFailure(Call<DataWilayah> call, Throwable t) {
        Toast.makeText(this, "fail", Toast.LENGTH_LONG).show();
    }
}
