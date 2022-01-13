package com.instagrame.apicallingretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    AppCompatButton getdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getdata=findViewById(R.id.getdata);
        getdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Methods methods= RetrofitClient.getRetrofitInstance().create(Methods.class);
                Call<Model> call=methods.getAllData();

                call.enqueue(new Callback<Model>() {
                    @Override
                    public void onResponse(Call<Model> call, Response<Model> response) {

                        Toast.makeText(MainActivity.this, ""+response.code(), Toast.LENGTH_SHORT).show();

                        ArrayList<Model.data> allData=response.body().getData();

                        for (Model.data data1:allData){
                            Toast.makeText(MainActivity.this, ""+data1.getEmail(), Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onFailure(Call<Model> call, Throwable t) {
                        Toast.makeText(MainActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();


                    }
                });
            }
        });
    }
}