package com.mobile.vocabulary.column

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.mobile.vocabulary.R
import com.mobile.vocabulary.vocabulary.Vocabulary
import com.mobile.vocabulary.vocabulary.VocabularyView
import kotlinx.android.synthetic.main.fragment_card_view.view.*

class ColumnRecyclerAdapter (private var vocabularies: List<Vocabulary>, private var activity: FragmentActivity) :
    RecyclerView.Adapter<ColumnRecyclerAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val vocabTitle: TextView = itemView.findViewById(R.id.cardTitle)

        init {
            itemView.setOnClickListener { v: View ->
                openVocabularyFragment(adapterPosition)
            }

            itemView.openButton.setOnClickListener {
                Toast.makeText(itemView.context, "You clicked on button \"${vocabularies[adapterPosition].word}\"", Toast.LENGTH_SHORT).show()
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
        var ft: FragmentTransaction = activity.supportFragmentManager.beginTransaction()
        ft.replace(R.id.frame, VocabularyView(vocabularies[index].word))
        ft.commit()
        ft.addToBackStack(null);
    }
}