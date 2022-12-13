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
import com.mobile.vocabulary.infra.network.VocabularyApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class CommentRecyclerAdapter (private var comments: ArrayList<Comment>, private var activity: FragmentActivity) :
    RecyclerView.Adapter<CommentRecyclerAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val comment: TextView = itemView.findViewById(R.id.id_comment_text)

        init {
            var deleteCommentButton: ImageView = itemView.findViewById(R.id.id_image_delete_comment)
            deleteCommentButton.setOnClickListener {
                deleteComment(adapterPosition, itemView.context)
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

    private fun deleteComment(index: Int, context: Context) {
        var id: UUID = comments[index].id!!

        VocabularyApi.retrofitService
            .deleteCommentById(id)
            .enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        Toast.makeText(context, "Comment deleted.", Toast.LENGTH_SHORT).show()
                        comments.removeAt(index)
                        notifyDataSetChanged()
                    }
                }
                override fun onFailure(call: Call<Void>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }
}