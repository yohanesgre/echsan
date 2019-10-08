package com.vira.echsan.ui.fragment

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentTabHost
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TabHost
import com.vira.echsan.R
import com.vira.echsan.ui.activity.MainActivity

class BookingsFragment : FragmentActivity(){
    lateinit var fragmentTabHost: FragmentTabHost

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_bookings)
        fragmentTabHost = findViewById(R.id.tabhost_bookings)
        fragmentTabHost.setup(this, supportFragmentManager, R.id.container_fragment_bookings)
        fragmentTabHost.addTab(fragmentTabHost.newTabSpec(getString(R.string.string_tab_bookings_commpleted))
            .setIndicator(getString(R.string.string_tab_bookings_commpleted), null), BookingContentFragment::class.java, null )
        fragmentTabHost.addTab(fragmentTabHost.newTabSpec(getString(R.string.string_tab_bookings_process))
            .setIndicator(getString(R.string.string_tab_bookings_process), null), BookingContentFragment::class.java, null)
        fragmentTabHost.addTab(fragmentTabHost.newTabSpec(getString(R.string.string_tab_bookings_process))
            .setIndicator(getString(R.string.string_tab_bookings_process), null), BookingContentFragment::class.java, null)
    }

    companion object {
        fun newInstance() : BookingsFragment = BookingsFragment()
    }
}
