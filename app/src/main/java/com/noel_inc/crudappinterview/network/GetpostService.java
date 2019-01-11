package com.noel_inc.crudappinterview.network;

import com.noel_inc.crudappinterview.model.GetPosts;
import com.noel_inc.crudappinterview.model.Post;
import com.noel_inc.crudappinterview.model.Update;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface GetpostService {

    @GET("posts")
    Call<List<GetPosts>> getAllPosts();

    @POST("posts")
    Call<Post> savePost(@Body Post post);

    @PUT("posts/{post_id}")
    Call<Update> savePost(@Body Update update , @Path("post_id") String post_id );

    @DELETE("posts/{post_id}")
    Call<Void> deletePost(@Path("post_id") String post_id);




}
