package com.vira.echsan.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.RelativeLayout
import com.vira.echsan.R
import com.vira.echsan.model.recyclerview.BookingsModel
import kotlinx.android.synthetic.main.fragment_content_bookings.*

class BookingContentFragment : Fragment(){

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
    ): View = inflater.inflate(R.layout.recyclerview_content_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //rv_content_bookings.apply {
          //  layoutManager = LinearLayoutManager(activity)
            //adapter = com.vira.echsan.adapter.ListAdapter(dummyList)
        //}
    }

    companion object{
        fun newInstance(): BookingContentFragment = BookingContentFragment()
    }
}