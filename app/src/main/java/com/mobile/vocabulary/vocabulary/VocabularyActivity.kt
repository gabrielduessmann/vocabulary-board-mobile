package com.mobile.vocabulary.vocabulary

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.mobile.vocabulary.R
import com.mobile.vocabulary.infra.network.VocabularyApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class VocabularyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vocabulary)

        showVocabularyAndCommentsFragment()
    }

    private fun showVocabularyAndCommentsFragment() {
        var extras: Bundle? = intent.extras
        if (extras != null) {
            var vocabularyId = extras.getString("vocabularyId")
            if (vocabularyId == null) {
                showToastErrorNoVocabularyId()
            } else {
                findVocabularyById(UUID.fromString(vocabularyId))
            }
        }
    }

    private fun findVocabularyById(id: UUID) {
        VocabularyApi.retrofitService
            .getVocabularyById(id)
            .enqueue(object : Callback<Vocabulary> {
                override fun onResponse(call: Call<Vocabulary>, response: Response<Vocabulary>) {
                    if (response.isSuccessful) {
                        replaceFrameWithFragment(response.body())
                    }
                }
                override fun onFailure(call: Call<Vocabulary>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }

    private fun replaceFrameWithFragment(vocabulary: Vocabulary?) {
        if (vocabulary == null)  return

        var fm: FragmentManager = getSupportFragmentManager();
        var ft: FragmentTransaction = fm.beginTransaction();

        ft.replace(R.id.frame_vocabulary, VocabularyView(vocabulary))
        ft.commitNow()
    }

    private fun showToastErrorNoVocabularyId() {
        Toast.makeText(this, "No VocabularyId was found. Please try again.", Toast.LENGTH_SHORT).show()
    }
}