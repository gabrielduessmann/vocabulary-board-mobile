package com.mobile.vocabulary.vocabulary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mobile.vocabulary.R

class VocabularyView : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vocabulary_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var columns = fetchColumns()
    }

    fun fetchColumns(): MutableList<String> {
        var allColumns = mutableListOf<String>()
        allColumns.add("Week 1")
        allColumns.add("Week 2")
        allColumns.add("Week 3")

        return allColumns
    }
}