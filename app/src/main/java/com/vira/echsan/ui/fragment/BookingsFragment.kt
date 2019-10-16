package com.vira.echsan.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTabHost
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vira.echsan.R
import com.vira.echsan.util.addPositionTab

class BookingsFragment : Fragment(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentTabHost(activity).apply{
            setup(activity, childFragmentManager, R.layout.fragment_bookings)
            addPositionTab(0)
            addPositionTab(1)
            addPositionTab(2)
        }
    }

    companion object {
        fun newInstance() : BookingsFragment = BookingsFragment()
    }
}
