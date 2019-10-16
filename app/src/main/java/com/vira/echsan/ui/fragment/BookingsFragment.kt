package com.vira.echsan.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTabHost
import com.vira.echsan.R
import com.vira.echsan.util.addPositionTab
import kotlinx.android.synthetic.main.fragment_bookings.*

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
        fun newInstance() : BookingsFragment = BookingsFragment()
    }
}
