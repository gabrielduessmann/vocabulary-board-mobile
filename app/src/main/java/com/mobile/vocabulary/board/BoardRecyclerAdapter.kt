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
import android.content.DialogInterface as DialogInterface1

class BoardRecyclerAdapter (private var columns: List<Column>, private var activity: FragmentActivity) :
    RecyclerView.Adapter<BoardRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View, parent: ViewGroup) : RecyclerView.ViewHolder(itemView) {
        val columnTitle: TextView = itemView.findViewById(R.id.id_column_title)

        init {
            itemView.setOnClickListener { v: View ->
                val position: Int = adapterPosition
                Toast.makeText(itemView.context, "You clicked on country \"${columns[position].title}\"", Toast.LENGTH_SHORT).show()
            }


            itemView.id_button_add_vocab.setOnClickListener { v: View ->
                var alert: AlertDialog.Builder = AlertDialog.Builder(activity)

                val dialogView = LayoutInflater.from(activity).inflate(R.layout.fragment_dialog_add_vocabulary, parent, false)


                alert.setView(dialogView)
                    .setTitle("Type the new vocabulary")
                    .setPositiveButton("Add", DialogInterface1.OnClickListener { dialogInterface, i ->
                        val newVocabulary: EditText = dialogView.findViewById(R.id.id_dialog_word)
                        Toast.makeText(activity, "Word ${newVocabulary.text} added!", Toast.LENGTH_SHORT).show()

                        var newVocab: Vocabulary = Vocabulary(null, newVocabulary.text.toString(), "desc")
                        val call: Call<Vocabulary> = VocabularyApi.retrofitService.addVocabulary(newVocab)

                        call.enqueue(object : Callback<Vocabulary> {
                            override fun onResponse(call: Call<Vocabulary>, response: Response<Vocabulary>) {
                                bind()
                            }
                            override fun onFailure(call: Call<Vocabulary>, t: Throwable) {
                                t.printStackTrace()
                            }
                        })


                    })
                    .setNegativeButton("Close", DialogInterface1.OnClickListener { dialogInterface, i -> })

                alert.create().show()
            }
        }

        fun bind() {
            var columnId: UUID = columns[adapterPosition].id
            val call: Call<List<Vocabulary>> = VocabularyApi.retrofitService.getVocabulariesByColumnId(columnId)

            var loader: ProgressBar = itemView.findViewById(R.id.progress_bar)

            call.enqueue(object : Callback<List<Vocabulary>> {
                override fun onResponse(call: Call<List<Vocabulary>>, response: Response<List<Vocabulary>>) {
                    if (response.isSuccessful) {


                        loader.visibility = View.GONE

                        var data = response.body() as ArrayList<Vocabulary>

                        itemView.id_column_recyclerView.apply {
                            layoutManager = LinearLayoutManager(itemView.context)
                            adapter = ColumnRecyclerAdapter(data, activity)
                        }
                    }
                }
                override fun onFailure(call: Call<List<Vocabulary>>, t: Throwable) {
                    loader.visibility = View.GONE
                    t.printStackTrace()
                }
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.fragment_column_view, parent, false)
        return ViewHolder(v, parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var column: Column = columns.get(position)
        holder.columnTitle.text = column.title
        holder.bind()

    }

    override fun getItemCount(): Int {
        return columns.size
    }

}