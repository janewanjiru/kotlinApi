package com.example.myposts
import org.w3c.dom.Comment
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path




interface ApiInterface {
    @GET("posts")
    fun getPosts(): Call<Posts>

    @GET("posts/{postId}")
    fun getPost(@Path("postId") Id:Int):Call<Posts>

    @GET("Posts/{postId}/Comments")
    fun getComments(@Path("postId")Id:Int):Call<Comment>


}