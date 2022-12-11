package com.mobile.vocabulary.board

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobile.vocabulary.R
import com.mobile.vocabulary.column.Column
import com.mobile.vocabulary.column.ColumnRecyclerAdapter
import com.mobile.vocabulary.infra.network.VocabularyApi
import com.mobile.vocabulary.vocabulary.Vocabulary
import kotlinx.android.synthetic.main.fragment_column_view.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class BoardRecyclerAdapter (private var columns: List<Column>, private var activity: FragmentActivity) :
    RecyclerView.Adapter<BoardRecyclerAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View, parent: ViewGroup) : RecyclerView.ViewHolder(itemView) {

        val columnTitle: TextView = itemView.findViewById(R.id.id_column_title)

        init {
            itemView.setOnClickListener { v: View ->
                val position: Int = adapterPosition
                Toast.makeText(itemView.context, "You clicked on country \"${columns[position].title}\"", Toast.LENGTH_SHORT).show()
            }
            setClickListenerButtonAddNewVocab(itemView, parent, adapterPosition+1) // FIXME - understand why is necessary to sum +1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.fragment_column_view, parent, false)
        return ViewHolder(v, parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, index: Int) {
        var column: Column = columns.get(index)
        holder.columnTitle.text = column.title

        loadVocabulariesByColumnId(holder.itemView, holder.adapterPosition)

    }

    override fun getItemCount(): Int {
        return columns.size
    }

    private fun getColumnIdByIndexPosition(index: Int): UUID {
        return columns[index].id
    }

    private fun loadVocabulariesByColumnId(itemView: View, index: Int) {
        var loader: ProgressBar = itemView.findViewById(R.id.progress_bar)
        showLoader(loader)

        var columnId: UUID = getColumnIdByIndexPosition(index)

        VocabularyApi.retrofitService
            .getVocabulariesByColumnId(columnId)
            .enqueue(object : Callback<List<Vocabulary>> {
                override fun onResponse(call: Call<List<Vocabulary>>, response: Response<List<Vocabulary>>) {
                    if (response.isSuccessful) {
                        hideLoader(loader)
                        var data = response.body() as ArrayList<Vocabulary>
                        setColumnRecyclerViewAdapter(itemView, data)
                    }
                }
                override fun onFailure(call: Call<List<Vocabulary>>, t: Throwable) {
                    hideLoader(loader)
                    t.printStackTrace()
                }
            })
    }

    private fun hideLoader(loader: ProgressBar) {
        loader.visibility = View.GONE
    }

    private fun showLoader(loader: ProgressBar) {
        loader.visibility = View.VISIBLE
    }

    private fun setColumnRecyclerViewAdapter(itemView: View, vocabularies: ArrayList<Vocabulary>) {
        itemView.id_column_recyclerView.apply {
            layoutManager = LinearLayoutManager(itemView.context)
            adapter = ColumnRecyclerAdapter(vocabularies, activity)
        }
    }

    private fun setClickListenerButtonAddNewVocab(itemView: View, viewGroupParent: ViewGroup, index: Int) {
        var dialogView = inflateDialogView(viewGroupParent)

        itemView.id_button_add_vocab.setOnClickListener { v: View ->
            AlertDialog.Builder(activity)
                .setView(dialogView)
                .setTitle("New vocabulary")
                .setPositiveButton("Add", { dialogInterface, i -> addNewVocab(itemView, dialogView, index) })
                .setNegativeButton("Close", { dialogInterface, i -> })
                .create()
                .show()
        }
    }

    private fun inflateDialogView(viewGroupParent: ViewGroup): View {
        return LayoutInflater.from(activity).inflate(R.layout.fragment_dialog_add_vocabulary, viewGroupParent, false)
    }

    private fun addNewVocab(columnView: View, dialogView: View, index: Int) {
        var word: EditText = dialogView.findViewById(R.id.id_dialog_word)
        var newVocab = Vocabulary(null, word.text.toString(), "desc")

        VocabularyApi.retrofitService
            .addVocabulary(newVocab)
            .enqueue(object : Callback<Vocabulary> {
                override fun onResponse(call: Call<Vocabulary>, response: Response<Vocabulary>) {
                    showToastVocabAdded()
                    loadVocabulariesByColumnId(columnView, index)
                }
                override fun onFailure(call: Call<Vocabulary>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }

    private fun showToastVocabAdded() {
        Toast.makeText(activity, "New vocabulary added.", Toast.LENGTH_SHORT).show()
    }

}