package com.noel_inc.crudappinterview.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.noel_inc.crudappinterview.R;
import com.noel_inc.crudappinterview.adapter.PostAdapter;
import com.noel_inc.crudappinterview.model.GetPosts;
import com.noel_inc.crudappinterview.network.ApiUtils;
import com.noel_inc.crudappinterview.network.GetpostService;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.security.AccessController.doPrivileged;

public class MainActivity extends AppCompatActivity {

    private PostAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDoalog;
    private GetpostService mService;

    FloatingActionButton fab;

    @Override
    protected void onCreate (Bundle savedInstanceState)  {
        super.onCreate( savedInstanceState);
        setContentView(R.layout.activity_main);


        mService = ApiUtils.getPostService();

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(),AddPostActivity.class);
                startActivity(intent);
            }
        });



        progressDoalog = new ProgressDialog(MainActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();
        loadAnswers();


    }



    public void loadAnswers() {
        mService.getAllPosts().enqueue(new Callback<List<GetPosts>>() {
            @Override
            public void onResponse(Call<List<GetPosts>> call, Response<List<GetPosts>> response) {

                if(response.isSuccessful()) {

                    progressDoalog.dismiss();

                    List<GetPosts> postsX = response.body();
                    recyclerView = findViewById(R.id.post_recycler_view);
                    adapter = new PostAdapter(postsX, getBaseContext());
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapter);
                    Log.d("MainActivity", "posts loaded from API");
                }else {
                    int statusCode  = response.code();
                    // handle request errors depending on status code
                    Log.d("MainActivity", "posts didnt loaded from API");
                }
            }

            @Override
            public void onFailure(Call<List<GetPosts>> call, Throwable t) {

            }
        });
    }




}
