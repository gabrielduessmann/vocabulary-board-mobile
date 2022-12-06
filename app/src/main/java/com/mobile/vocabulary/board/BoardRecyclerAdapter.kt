package com.mobile.vocabulary.board

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobile.vocabulary.R
import com.mobile.vocabulary.column.ColumnRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_column_view.view.*

class BoardRecyclerAdapter (private var columns: List<String>) :
    RecyclerView.Adapter<BoardRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val columnTitle: TextView = itemView.findViewById(R.id.id_column_title)

        init {
            itemView.setOnClickListener { v: View ->
                val position: Int = adapterPosition
                Toast.makeText(itemView.context, "You clicked on country \"${columns[position]}\"", Toast.LENGTH_SHORT).show()
            }
        }

        fun bind() {
            val childMembersAdapter = ColumnRecyclerAdapter(listOf("teste"))
            itemView.id_column_recyclerView.layoutManager = LinearLayoutManager(itemView.context)
            itemView.id_column_recyclerView.adapter = childMembersAdapter

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.fragment_column_view, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.columnTitle.text = columns[position]
        holder.bind()

    }

    override fun getItemCount(): Int {
        return columns.size
    }
}