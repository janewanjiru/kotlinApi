package com.example.myposts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Comment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class commentActivity : AppCompatActivity() {
    var postId = 0
    lateinit var tvPostList: TextView
    lateinit var tvPostTitle: TextView
    lateinit var tvPostBody: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)
        postId = intent.getIntExtra("POST_ID", 0)
        castViews()
        getPost()
        getComment()
    }

    fun castViews() {
        tvPostTitle = findViewById(R.id.tvTittle)
        tvPostBody = findViewById(R.id.tvbody)

    }

    fun getPost() {
        if (postId == 0)
            Toast.makeText(baseContext, "Post not found", Toast.LENGTH_LONG).show()
        finish()


        val apiClient = ApiClient.buildApiClient(ApiInterface::class.java)
        val request=apiClient.getPost((postId))
        request.enqueue(object : Callback<Posts> {
            override fun onResponse(call: Call<Posts>, response: Response<Posts>) {
                if (response. isSuccessful){
                    var post=response.body()
                    tvPostBody.text=post?.body
                    tvPostTitle.text=post?.title
                }
            }

            override fun onFailure(call: Call<Posts>, t: Throwable) {
                Toast.makeText(baseContext,t.message, Toast.LENGTH_LONG).show()

            }
        })
    }
    fun getComment(){
    var    rvComments=findViewById<RecyclerView>(R.id.rvComments)
        var retrofit=ApiClient.buildApiClient(ApiInterface::class.java)
        var request=retrofit.getComments(postId)

        request.enqueue(object : Callback<List<Comments>>{
            override fun onResponse(call: Call<List<Comments>?>, response: Response<List<Comments>?>) {
                if (response.isSuccessful){
                    var Comments=response.body()!!
                    var CommentsAdapter=CommentsRecyclerViewAdapter(Comments!!,baseContext)
                    rvComments.layoutManager=LinearLayoutManager(baseContext)
                    rvComments.adapter=CommentsAdapter
                }

            }

            override fun onFailure(call: Call<List<Comments>>, t: Throwable) {
             Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
            }
        })
    }
}

