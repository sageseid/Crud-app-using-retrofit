package com.noel_inc.crudappinterview.network;

import com.noel_inc.crudappinterview.model.GetPosts;
import com.noel_inc.crudappinterview.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GetpostService {

    @GET("posts")
    Call<List<GetPosts>> getAllPosts();

    @POST("posts")
    Call<Post> savePost(@Body Post post);
}
