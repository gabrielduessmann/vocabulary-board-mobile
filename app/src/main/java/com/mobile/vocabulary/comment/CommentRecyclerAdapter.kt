package com.mobile.vocabulary.comment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.mobile.vocabulary.R
import java.util.*

class CommentRecyclerAdapter (private var comments: List<Comment>, private var activity: FragmentActivity) :
    RecyclerView.Adapter<CommentRecyclerAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val comment: TextView = itemView.findViewById(R.id.id_comment_text)

        init {
            var deleteCommentButton: ImageView = itemView.findViewById(R.id.id_image_detele_comment)
            deleteCommentButton.setOnClickListener {
                deleteComment(comments[adapterPosition].id!!, itemView.context)
            }
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

    private fun deleteComment(commentId: UUID, context: Context) {
        Toast.makeText(context, "Comment with id \"${commentId}\" deleted", Toast.LENGTH_SHORT).show()
    }
}