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

        var words = fetchWords()

        id_column_recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
//            adapter = ColumnRecyclerAdapter(words, requireActivity())
        }
    }

    fun fetchWords(): MutableList<String> {
        var allWords = mutableListOf<String>()
        allWords.add("teste1")
        allWords.add("teste2")
        allWords.add("teste3")
        allWords.add("teste4")
        allWords.add("teste5")
        allWords.add("teste6")
        allWords.add("teste7")
        allWords.add("teste8")
        allWords.add("teste9")
        allWords.add("teste10")
        allWords.add("teste11")
        allWords.add("teste12")
        allWords.add("teste13")

        return allWords
    }
}