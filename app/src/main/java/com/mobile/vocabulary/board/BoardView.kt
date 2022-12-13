package com.mobile.vocabulary.board

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.mobile.vocabulary.R
import com.mobile.vocabulary.column.Column
import com.mobile.vocabulary.infra.network.VocabularyApi
import kotlinx.android.synthetic.main.fragment_board_view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BoardView : Fragment() {

    var snapHelper: SnapHelper? = null

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

        id_swipe_refresh_column.setOnRefreshListener {
            fetchColumns()
        }

        fetchColumns()
    }

    private fun fetchColumns() {
        VocabularyApi.retrofitService
            .getColumns()
            .enqueue(object : Callback<List<Column>> {
                override fun onResponse(call: Call<List<Column>>, response: Response<List<Column>>) {
                    if (response.isSuccessful) {
                        var data = response.body() as ArrayList<Column>
                        applyRecyclerAdapter(data)
                    }
                }
                override fun onFailure(call: Call<List<Column>>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }

    private fun applyRecyclerAdapter(columns: List<Column>) {
        id_board_recyclerView.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = BoardRecyclerAdapter(columns, requireActivity())
        }
        id_board_recyclerView.layoutManager!!.scrollToPosition(3)
        id_swipe_refresh_column.isRefreshing = false

        if (snapHelper == null) {
            snapHelper = PagerSnapHelper()
            snapHelper?.attachToRecyclerView(id_board_recyclerView)
        }
    }
}