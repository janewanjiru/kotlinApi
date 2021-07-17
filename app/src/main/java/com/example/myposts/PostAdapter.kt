package com.example.myposts


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class PostAdapter(var posts:List<Posts>,var context: Context): RecyclerView.Adapter<postItemViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): postItemViewHolder {
      val itemView=LayoutInflater.from(parent.context).inflate(R.layout.post_list_item,parent,false)
        return postItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: postItemViewHolder, position: Int) {
        val currentPost = posts.get(position)
        holder.tvTitle.text=currentPost.title
        holder. tvBody.text = currentPost.body
        holder.cvPost.setOnClickListener(){
        var intent = Intent(context,commentActivity::class.java)
            intent.putExtra("post_id",currentPost.id)
            context.startActivity(intent)
    }
    }

    override fun getItemCount(): Int {
     return posts.size
    }
}
class postItemViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    var tvTitle=itemView.findViewById<TextView>(R.id.tvtittle)
    var tvBody=itemView.findViewById<TextView>(R.id.tvBody)
    var cvPost=itemView.findViewById<TextView>(R.id.cvPost)
}
