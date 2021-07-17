package com.example.myposts

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CommentsRecyclerViewAdapter(var Comments:List<Comments>, var context: Context):RecyclerView.Adapter<commentsItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): commentsItemViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.post_list_item,parent,false)
        return commentsItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: commentsItemViewHolder, position: Int) {
        val currentComments = Comments.get(position)
        holder.tvName.text=currentComments.name
        holder. tvEmail.text = currentComments.Email
        holder.tvBodies.text=currentComments.body


    }


    override fun getItemCount(): Int {
        return Comments.size

    }
}
class commentsItemViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
    var tvName = itemView.findViewById<TextView>(R.id.tvName)
    var tvEmail = itemView.findViewById<TextView>(R.id.tvEmail)
    var tvBodies = itemView.findViewById<TextView>(R.id.tvBodies)


}
