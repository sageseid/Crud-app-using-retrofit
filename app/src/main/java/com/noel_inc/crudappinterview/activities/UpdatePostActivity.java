package com.noel_inc.crudappinterview.activities;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.noel_inc.crudappinterview.model.Update;
import com.noel_inc.crudappinterview.network.ApiUtils;
import com.noel_inc.crudappinterview.network.GetpostService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdatePostActivity  extends AppCompatActivity {



    ProgressDialog progressDoalog;
    Update update;
    private TextView mResponseTv;
    private GetpostService mAPIService;
    private String sessionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_update_post);

        final EditText titleEt =  findViewById(R.id.et_title);
        final EditText bodyEt = findViewById(R.id.et_body);
        Button submitBtn = findViewById(R.id.btn_submit);
        mResponseTv = (TextView) findViewById(R.id.tv_response);
        submitBtn.setText("Update");


        String sessionTitle= getIntent().getStringExtra("postTitleDetails");
        String sessionBody= getIntent().getStringExtra("postBodyDetails");
        sessionId = getIntent().getStringExtra("postIdDetails");


        titleEt.setText(sessionTitle);
        bodyEt.setText(sessionBody);

        mAPIService = ApiUtils.getPostService();





        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = titleEt.getText().toString().trim();
                String body = bodyEt.getText().toString().trim();
                if(!TextUtils.isEmpty(title) && !TextUtils.isEmpty(body)) {
                    progressDoalog = new ProgressDialog(UpdatePostActivity.this);
                    progressDoalog.setMessage("Loading....");
                    progressDoalog.show();

                    try {
                        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                    update = new Update(title,body,1,1);
                    sendUpdate(update);
                }
            }
        });
    }







    public void sendUpdate(Update update) {
        mAPIService.savePost(update , sessionId).enqueue(new Callback<Update>() {
            @Override
            public void onResponse(Call<Update> call, Response<Update> response) {



                    showResponse(String.valueOf(response.body()));
                    Log.e("TAG", "update submitted to API." );
                    progressDoalog.dismiss();

            }

            @Override
            public void onFailure(Call<Update> call, Throwable t) {
                Log.e("TAG", "Unable to update post to API.");
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