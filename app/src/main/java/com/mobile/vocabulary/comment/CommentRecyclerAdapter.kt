package com.mobile.vocabulary.comment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.mobile.vocabulary.R

class CommentRecyclerAdapter (private var comments: List<Comment>, private var activity: FragmentActivity) :
    RecyclerView.Adapter<CommentRecyclerAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val comment: TextView = itemView.findViewById(R.id.id_comment_text)

        init {
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.fragment_sentence_view, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.comment.text = comments[position].comment
    }

    override fun getItemCount(): Int {
        return comments.size
    }
}