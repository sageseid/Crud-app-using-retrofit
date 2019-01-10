package com.noel_inc.crudappinterview.network;

import com.noel_inc.crudappinterview.model.GetPosts;
import com.noel_inc.crudappinterview.model.Post;
import com.noel_inc.crudappinterview.model.Update;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface GetpostService {

    @GET("posts")
    Call<List<GetPosts>> getAllPosts();

    @POST("posts")
    Call<Post> savePost(@Body Post post);

    @PUT("posts/1")
    Call<Update> savePost(@Body Update update);
}
