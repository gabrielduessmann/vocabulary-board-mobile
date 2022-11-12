package com.mobile.vocabulary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.mobile.vocabulary.card.CardView
import com.mobile.vocabulary.column.ColumnView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showColumn();
    }

    fun showColumn() {
        var fm: FragmentManager = getSupportFragmentManager();
        var ft: FragmentTransaction = fm.beginTransaction();
        ft.replace(R.id.frame, ColumnView())
        ft.commitNow()
    }
}