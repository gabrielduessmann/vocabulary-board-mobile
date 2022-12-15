package com.mobile.vocabulary.vocabulary

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
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


class VocabularyView(var vocabulary: Vocabulary) : Fragment() {
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

        id_vocab_title.text = vocabulary.word
        fetchCommentsByVocabularyId()

        id_save_new_comment.setOnClickListener { v: View ->
            addNewComment(v)
        }

        id_image_close_vocabulary_activity.setOnClickListener {
            requireActivity().finish()
        }
    }

    private fun fetchCommentsByVocabularyId() {
        VocabularyApi.retrofitService
            .getCommentsByVocabularyId(vocabulary.id!!)
            .enqueue(object : Callback<List<Comment>> {
                override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                    if (response.isSuccessful) {
                        var data = response.body() as ArrayList<Comment>
                        setCommentRecyclerAdapter(data)
                    }
                }
                override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }

    private fun setCommentRecyclerAdapter(comments: ArrayList<Comment>) {
        id_comment_recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = CommentRecyclerAdapter(comments, requireActivity())
        }
    }

    private fun addNewComment(view: View) {
        var comment: String = id_comment_text.text.toString()
        var newComment = Comment(null, comment, vocabulary)

        cleanTextAndHideKeyboard(view)

        VocabularyApi.retrofitService
            .addComment(newComment)
            .enqueue(object : Callback<Comment> {
                override fun onResponse(call: Call<Comment>, response: Response<Comment>) {
                    fetchCommentsByVocabularyId()
                }
                override fun onFailure(call: Call<Comment>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }

    private fun cleanTextAndHideKeyboard(view: View) {
        id_comment_text.text = null

        val imm: InputMethodManager =
            requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}