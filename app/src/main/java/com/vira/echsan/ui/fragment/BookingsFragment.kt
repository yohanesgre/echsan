package com.vira.echsan.ui.fragment

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentTabHost
import android.support.v4.app.FragmentTransaction
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TabHost
import com.vira.echsan.R
import com.vira.echsan.ui.activity.MainActivity

class BookingsFragment : Fragment(){
    lateinit var fragmentTabHost: FragmentTabHost

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_bookings, container, false)
        fragmentTabHost = view.findViewById(R.id.tabhost_bookings)
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentTabHost.setup(activity!!.applicationContext, childFragmentManager, R.id.container_fragment_bookings)
        fragmentTabHost.addTab(fragmentTabHost.newTabSpec(getString(R.string.string_tab_bookings_commpleted))
            .setIndicator(getString(R.string.string_tab_bookings_commpleted), null), BookingContentFragment::class.java, null )
        fragmentTabHost.addTab(fragmentTabHost.newTabSpec(getString(R.string.string_tab_bookings_process))
            .setIndicator(getString(R.string.string_tab_bookings_process), null), BookingContentFragment::class.java, null)
        fragmentTabHost.addTab(fragmentTabHost.newTabSpec(getString(R.string.string_tab_bookings_process))
            .setIndicator(getString(R.string.string_tab_bookings_process), null), BookingContentFragment::class.java, null)
        /*val childFragment: Fragment = BookingContentFragment()
        val transaction: FragmentTransaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.container_fragment_bookings, childFragment).commit()*/
    }

    companion object {
        fun newInstance() : BookingsFragment = BookingsFragment()
    }
}
