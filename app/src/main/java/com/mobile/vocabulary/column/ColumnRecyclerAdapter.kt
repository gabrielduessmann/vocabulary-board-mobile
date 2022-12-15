package com.mobile.vocabulary.column

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.mobile.vocabulary.R
import com.mobile.vocabulary.infra.network.VocabularyApi
import com.mobile.vocabulary.vocabulary.Vocabulary
import com.mobile.vocabulary.vocabulary.VocabularyActivity
import kotlinx.android.synthetic.main.fragment_card_view.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ColumnRecyclerAdapter (private var vocabularies: ArrayList<Vocabulary>, private var activity: FragmentActivity) :
    RecyclerView.Adapter<ColumnRecyclerAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val vocabTitle: TextView = itemView.findViewById(R.id.cardTitle)


        init {
            itemView.setOnClickListener { v: View ->
                openVocabularyFragment(adapterPosition)
            }

            itemView.openButton.setOnClickListener {
                moveCardToNextColumn(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.fragment_card_view, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.vocabTitle.text = vocabularies[position].word
    }

    override fun getItemCount(): Int {
        return vocabularies.size
    }

    private fun openVocabularyFragment(index: Int) {
        var intentToOpenNewActivity: Intent = Intent(this.activity, VocabularyActivity::class.java)
        intentToOpenNewActivity.putExtra("vocabularyId", vocabularies[index].id.toString())
        this.activity.startActivity(intentToOpenNewActivity)
    }

    private fun moveCardToNextColumn(index: Int) {
        VocabularyApi.retrofitService
            .moveCardToNextColumn(vocabularies[index].id!!)
            .enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    showToastMovedCard(vocabularies[index].word)
                    vocabularies.removeAt(index)

                    // FIXME - update current column, but not the next one which the card is added
                    notifyDataSetChanged()
                }
                override fun onFailure(call: Call<Void>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }

    private fun showToastMovedCard(word: String) {
        Toast.makeText(activity, "Vocabulary '${word}' moved to next column.", Toast.LENGTH_SHORT).show()
    }
}