package com.vira.echsan.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.vira.echsan.R
import com.vira.echsan.adapter.ListAdapter
import com.vira.echsan.model.recyclerview.BookingsModel
import kotlinx.android.synthetic.main.fragment_bookings_child.view.*


class BookingsChildFragment : androidx.fragment.app.Fragment(), View.OnClickListener {

    private val dummyList = listOf(
        BookingsModel(1, "9 Oktober 2019", "Echsan", "10 Oktober 2019 - 20 Oktober 2019") ,
        BookingsModel(2, "13 Oktober 2019", "Echsan", "10 Oktober 2019 - 20 Oktober 2019")
    )
    private val dummyList2 = listOf(
        BookingsModel(2, "11 Oktober 2019", "Echsan", "10 Oktober 2019 - 20 Oktober 2019"),
        BookingsModel(1, "12 Oktober 2019", "Echsan", "10 Oktober 2019 - 20 Oktober 2019")
    )
    private val dummyList3 = listOf(
        BookingsModel(1, "10 Oktober 2019", "Echsan", "10 Oktober 2019 - 20 Oktober 2019"),
        BookingsModel(2, "11 Oktober 2019", "Echsan", "10 Oktober 2019 - 20 Oktober 2019")
    )

    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?): View? {
        position = arguments?.getInt(POSITION_KEY) ?: 0

        val root = inflater.inflate(R.layout.fragment_bookings_child, container, false)

        /*(root?.findViewById(R.id.textViewPosition) as TextView).apply {
            text = String.format("%s", position)
            setOnClickListener(this@BookingsChildFragment)
        }*/

        root.rv_content_bookings.apply {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
            when(position){
                TAB_COMPLETED->adapter = ListAdapter(dummyList)
                TAB_PROCESS->adapter = ListAdapter(dummyList2)
                TAB_CANCELED->adapter = ListAdapter(dummyList3)
            }
        }

        return root
    }

    override fun onClick(v: View) {
        Toast.makeText(v.context, "Clicked Position: $position", Toast.LENGTH_LONG).show()
    }

    companion object {
        const val POSITION_KEY = "FragmentPositionKey"
        const val TAB_COMPLETED = 0
        const val TAB_PROCESS = 1
        const val TAB_CANCELED = 2
        fun newInstance(args: Bundle): BookingsChildFragment = BookingsChildFragment().apply { arguments = args }
    }
}

/*
class BookingsChildFragment : Fragment(){

    private val dummyList = listOf(
        BookingsModel(1, "9 Oktober 2019", "Echsan", "10 Oktober 2019 - 20 Oktober 2019")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.cardview_bookings, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //rv_content_bookings.apply {
          //  layoutManager = LinearLayoutManager(activity)
            //adapter = com.vira.echsan.adapter.ListAdapter(dummyList)
        //}
    }

    companion object{
        fun newInstance(): BookingsChildFragment = BookingsChildFragment()
    }
}*/