package com.noel_inc.crudappinterview;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;


import com.noel_inc.crudappinterview.adapter.PostAdapter;
import com.noel_inc.crudappinterview.model.RetroPosts;
import com.noel_inc.crudappinterview.network.ApiUtils;
import com.noel_inc.crudappinterview.network.GetpostService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.security.AccessController.doPrivileged;
import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    private PostAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDoalog;
    private GetpostService mService;

    @Override
    protected void onCreate (Bundle savedInstanceState)  {
        super.onCreate( savedInstanceState);
        setContentView(R.layout.activity_main);


        mService = ApiUtils.getPostService();



        progressDoalog = new ProgressDialog(MainActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();
        loadAnswers();

    }



    public void loadAnswers() {
        mService.getAllPosts().enqueue(new Callback<List<RetroPosts>>() {
            @Override
            public void onResponse(Call<List<RetroPosts>> call, Response<List<RetroPosts>> response) {

                if(response.isSuccessful()) {

                    progressDoalog.dismiss();

                    List<RetroPosts> postsX = response.body();
                    recyclerView = findViewById(R.id.post_recycler_view);
                    adapter = new PostAdapter(postsX, getBaseContext());
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapter);
                    Log.e("MainActivity", "posts loaded from API");
                }else {
                    int statusCode  = response.code();
                    // handle request errors depending on status code
                    Log.e("MainActivity", "posts didnt loaded from API");
                }
            }

            @Override
            public void onFailure(Call<List<RetroPosts>> call, Throwable t) {

            }
        });
    }
}
