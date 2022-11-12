package com.mobile.vocabulary.column


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.mobile.vocabulary.R
import com.mobile.vocabulary.card.CardView


class ColumnView : Fragment {

    constructor() : super(R.layout.fragment_column_view)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showCard()
    }

    fun showCard() {
        var fm: FragmentManager = this.childFragmentManager;
        var ft: FragmentTransaction = fm.beginTransaction();
        ft.replace(R.id.frameCard, CardView())
        ft.commitNow()
    }

}