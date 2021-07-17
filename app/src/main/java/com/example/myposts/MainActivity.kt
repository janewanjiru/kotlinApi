package com.example.myposts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var repost:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getPosts()
    }
    fun getPosts(){
        rvposts=findViewById<TextView>(R.id.rvPosts)
        var retrofit=ApiClient.buildApiClient(ApiInterface::class.java)
        var request=retrofit.getPosts()

        request.enqueue(object : Callback<List<Posts>?>{
            override fun onResponse(call: Call<List<Posts>>, response:
            Response<List<Posts>?>) {
                if (response.isSuccessful){
                    var posts=response.body()
                    var postsAdapter=PostAdapter(posts!!,baseContext)
                    rvPosts.LayoutManager=LinearLayoutManager(baseContext)
                    rvPosts.adapter=postsAdapter
                }

            }

            override fun onFailure(call: Call<List<Posts>?>, t: Throwable) {
                Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()

            }
        })
    }
}