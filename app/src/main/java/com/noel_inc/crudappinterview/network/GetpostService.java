package com.noel_inc.crudappinterview.network;

import com.noel_inc.crudappinterview.model.RetroPosts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetpostService {

    @GET("posts")
    Call<List<RetroPosts>> getAllPosts();
}
