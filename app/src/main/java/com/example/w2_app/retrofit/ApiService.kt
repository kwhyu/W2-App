package com.example.w2_app.retrofit

import com.example.w2_app.data.DetailUserResponse
import com.example.w2_app.data.FollowingFollowerResponse
import com.example.w2_app.data.GithubResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    fun getUserSearch(
        @Query("q") query: String
    ): Call<GithubResponse>

    @GET("users/{username}")
    fun getUserDetail(
        @Path("username") query: String
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    fun getUserFollower(
        @Path("username") username: String
    ): Call<List<FollowingFollowerResponse>>

    @GET("users/{username}/following")
    fun getUserFollowing(
        @Path("username") username: String
    ): Call<List<FollowingFollowerResponse>>

}