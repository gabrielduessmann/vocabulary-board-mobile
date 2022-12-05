package com.mobile.vocabulary.column


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.vocabulary.R
import kotlinx.android.synthetic.main.fragment_column_view.*


class ColumnView() : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_column_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        id_column_recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = ColumnRecyclerAdapter(listOf("teste1", "teste2", "teste3"))
        }
    }
}