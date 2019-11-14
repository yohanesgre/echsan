package com.vira.echsan.ui.fragments.bookings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.vira.echsan.R
import com.vira.echsan.databinding.FragmentBookingsBinding
import com.vira.echsan.adapters.BookingsPagerFragmentAdapter
import com.vira.echsan.adapters.CANCELED_FRAGMENT_INDEX
import com.vira.echsan.adapters.COMPLETED_FRAGMENT_INDEX
import com.vira.echsan.adapters.PROCESS_FRAGMENT_INDEX

class BookingsFragment : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentBookingsBinding.inflate(inflater, container, false)
        context?: return binding.root
        val tabLayout = binding.tabs
        val viewPager = binding.viewPager

        viewPager.adapter = BookingsPagerFragmentAdapter(this)

        TabLayoutMediator(tabLayout, viewPager) {tabs, position->
            tabs.text = getTabTitle(position)
        }.attach()
        return binding.root
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            COMPLETED_FRAGMENT_INDEX -> getString(R.string.string_tab_bookings_commpleted)
            PROCESS_FRAGMENT_INDEX -> getString(R.string.string_tab_bookings_process)
            CANCELED_FRAGMENT_INDEX -> getString(R.string.string_tab_bookings_canceled)
            else -> null
        }
    }

    companion object {
        fun newInstance() : BookingsFragment =
            BookingsFragment()
    }
}
