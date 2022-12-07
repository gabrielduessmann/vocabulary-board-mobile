package com.mobile.vocabulary.board

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.mobile.vocabulary.R
import com.mobile.vocabulary.column.Column
import com.mobile.vocabulary.infra.network.VocabularyApi
import kotlinx.android.synthetic.main.fragment_board_view.*
import kotlinx.coroutines.awaitAll
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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

        fetchColumns()
    }

    fun fetchColumns(): List<Column> {

        val callColumns: Call<List<Column>> = VocabularyApi.retrofitService.getColumns()

        var data = ArrayList<Column>()

        callColumns.enqueue(object : Callback<List<Column>> {
            override fun onResponse(call: Call<List<Column>>, response: Response<List<Column>>) {
                if (response.isSuccessful) {
                    data = response.body() as ArrayList<Column>
                    applyRecyclerAdapter(data)
                }
            }
            override fun onFailure(call: Call<List<Column>>, t: Throwable) {
                t.printStackTrace()
            }
        })

        return data
    }

    private fun applyRecyclerAdapter(columns: List<Column>) {
        id_board_recyclerView.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = BoardRecyclerAdapter(columns, requireActivity())
        }

        var snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(id_board_recyclerView)
    }
}