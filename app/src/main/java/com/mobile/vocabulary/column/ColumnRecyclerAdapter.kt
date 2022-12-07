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

class ColumnRecyclerAdapter (private var titles: List<Vocabulary>, private var activity: FragmentActivity) :
    RecyclerView.Adapter<ColumnRecyclerAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemTitle: TextView = itemView.findViewById(R.id.cardTitle)

        init {
            itemView.setOnClickListener { v: View ->
                openVocabularyModal(adapterPosition)
            }

            itemView.openButton.setOnClickListener {
                val position: Int = adapterPosition
                Toast.makeText(itemView.context, "You clicked on button \"${titles[position].word}\"", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.fragment_card_view, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemTitle.text = titles[position].word

    }

    override fun getItemCount(): Int {
        return titles.size
    }

    private fun openVocabularyModal(position: Int) {
        var ft: FragmentTransaction = activity.supportFragmentManager.beginTransaction()
        ft.replace(R.id.frame, VocabularyView(titles[position].word))
        ft.commit()
        ft.addToBackStack(null);
    }
}