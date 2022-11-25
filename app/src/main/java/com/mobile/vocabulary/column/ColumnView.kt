package com.mobile.vocabulary.column


import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.mobile.vocabulary.R
import com.mobile.vocabulary.card.CardView
import com.mobile.vocabulary.databinding.FragmentColumnViewBinding


class ColumnView : Fragment {

    var _binding: FragmentColumnViewBinding? = null
    val binding get() = _binding!!

    constructor() : super(R.layout.fragment_column_view)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listCards()
    }

    fun showCard() {
        var fm: FragmentManager = this.childFragmentManager;
        var ft: FragmentTransaction = fm.beginTransaction();
        ft.replace(R.id.frameCard, CardView())
        ft.commitNow()
    }

    fun listCards() {
//        var listCards: ListView? = getActivity()?.findViewById(R.id.listViewCards)
        var listCards = binding.listViewCards

        var array = arrayOf("Gabriel", "Marcos", "Eduardo")
//        var adapter = ArrayAdapter (requireActivity().baseContext, R.layout.fragment_card_view, array)

        if (listCards == null) return

//        listCards.setAdapter(adapter)

        listCards.setAdapter(object : BaseAdapter() {
            override fun getCount(): Int {
                return array.size
            }

            override fun getItem(i: Int): Any {
                return array[i]
            }

            override fun getItemId(i: Int): Long {
                return 0
            }

            override fun getView(i: Int, view: View?, viewGroup: ViewGroup?): View {
                TODO("Not yet implemented")

                if (view == null) {
                    view = getLayoutInflater().inflate(R.layout.fragment_card_view, viewGroup, false)
                }
                var title: TextView = view!!.findViewById(R.id.cardTitle)
                title.setText(array[i])

                return view!!
            }
        })

        /*
        listCards.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                TODO("Not yet implemented")
                val itemValue: String = listCards.getItemAtPosition(position) as String
                Toast.makeText(requireActivity().applicationContext, "Value: $itemValue", Toast.LENGTH_SHORT).show()
            }
        }

         */
    }

}