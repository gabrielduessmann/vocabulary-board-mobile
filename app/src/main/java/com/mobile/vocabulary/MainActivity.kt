package com.mobile.vocabulary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.mobile.vocabulary.board.BoardView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showBoard();
    }

    fun showBoard() {
        var fm: FragmentManager = getSupportFragmentManager();
        var ft: FragmentTransaction = fm.beginTransaction();

        ft.replace(R.id.frame, BoardView())
        ft.commitNow()
    }
}