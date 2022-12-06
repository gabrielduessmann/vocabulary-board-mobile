package com.mobile.vocabulary.board

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.mobile.vocabulary.R
import kotlinx.android.synthetic.main.fragment_board_view.*


class BoardView : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_board_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var columns = fetchColumns()

        id_board_recyclerView.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = BoardRecyclerAdapter(columns, requireActivity())
        }

        var snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(id_board_recyclerView)
    }

    fun fetchColumns(): MutableList<String> {
        var allColumns = mutableListOf<String>()
        allColumns.add("Week 1")
        allColumns.add("Week 2")
        allColumns.add("Week 3")

        return allColumns
    }
}