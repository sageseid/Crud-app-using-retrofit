package com.noel_inc.crudappinterview.activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.noel_inc.crudappinterview.R;
import com.noel_inc.crudappinterview.model.Post;
import com.noel_inc.crudappinterview.network.ApiUtils;
import com.noel_inc.crudappinterview.network.GetpostService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddPostActivity  extends AppCompatActivity {

    Post post;
    private TextView mResponseTv;
    private GetpostService mAPIService;

    @Override
    protected void onCreate (Bundle savedInstanceState)  {
        super.onCreate( savedInstanceState);
        setContentView(R.layout.add_post);
        final EditText titleEt =  findViewById(R.id.et_title);
        final EditText bodyEt = findViewById(R.id.et_body);
        Button submitBtn = findViewById(R.id.btn_submit);
        mResponseTv = (TextView) findViewById(R.id.tv_response);

        mAPIService = ApiUtils.getPostService();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = titleEt.getText().toString().trim();
                String body = bodyEt.getText().toString().trim();
                if(!TextUtils.isEmpty(title) && !TextUtils.isEmpty(body)) {


                    try {
                        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                    post = new Post(title,body,1);
                    sendPost(post);
                }
            }
        });
    }


    public void sendPost(Post post) {
        mAPIService.savePost(post).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                if(response.isSuccessful()) {
                    showResponse(response.body().toString());
                    Log.d("TAG", "post submitted to API." + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.d("TAG", "Unable to submit post to API.");
            }
        });
    }

    public void showResponse(String response) {
        if(mResponseTv.getVisibility() == View.GONE) {
            mResponseTv.setVisibility(View.VISIBLE);
        }
        mResponseTv.setText(response);
    }

}