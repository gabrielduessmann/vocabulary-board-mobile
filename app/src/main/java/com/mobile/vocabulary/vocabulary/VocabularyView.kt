package com.mobile.vocabulary.vocabulary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.vocabulary.R
import com.mobile.vocabulary.comment.Comment
import com.mobile.vocabulary.comment.CommentRecyclerAdapter
import com.mobile.vocabulary.infra.network.VocabularyApi
import kotlinx.android.synthetic.main.fragment_vocabulary_view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class VocabularyView(var vocabularyId: UUID) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vocabulary_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        id_vocab_title.text = vocabularyId.toString()

        fetchComments()
    }

    private fun fetchComments() {
        VocabularyApi.retrofitService
            .getCommentsByVocabularyId(vocabularyId)
            .enqueue(object : Callback<List<Comment>> {
                override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                    if (response.isSuccessful) {
                        var data = response.body() as ArrayList<Comment>
                        applyCommentRecyclerAdapter(data)
                    }
                }
                override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }

    private fun applyCommentRecyclerAdapter(comments: List<Comment>) {
        id_comment_recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = CommentRecyclerAdapter(comments, requireActivity())
        }
    }
}