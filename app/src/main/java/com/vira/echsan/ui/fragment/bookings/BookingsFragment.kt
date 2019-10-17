package com.vira.echsan.ui.fragment.bookings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTabHost
import com.vira.echsan.R
import com.vira.echsan.util.addPositionTab

class BookingsFragment : androidx.fragment.app.Fragment(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentTabHost(activity!!.application).apply{
            setup(activity!!, childFragmentManager, R.layout.fragment_bookings)
            addPositionTab(0)
            addPositionTab(1)
            addPositionTab(2)
        }
    }

    companion object {
        fun newInstance() : BookingsFragment =
            BookingsFragment()
    }
}
