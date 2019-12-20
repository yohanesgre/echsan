package com.vira.echsan.features.bookings.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.vira.echsan.databinding.FragmentBookingsBinding
import com.vira.echsan.di.Injectable
import com.vira.echsan.features.bookings.ACTIVE_FRAGMENT_INDEX
import com.vira.echsan.features.bookings.ARCHIVED_FRAGMENT_INDEX
import com.vira.echsan.features.bookings.BookingsPagerFragmentAdapter

class BookingsFragment : Fragment(), Injectable {

    private var userId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userId = arguments?.getInt("UserID") ?: 0
        println("UserID: ${userId}")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentBookingsBinding.inflate(inflater, container, false)
        context?: return binding.root
        val tabLayout = binding.tabs
        val viewPager = binding.viewPager

        viewPager.adapter =
            BookingsPagerFragmentAdapter(this, userId)

        TabLayoutMediator(tabLayout, viewPager) {tabs, position->
            tabs.text = getTabTitle(position)
        }.attach()
        return binding.root
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            ACTIVE_FRAGMENT_INDEX -> "ACTIVE"
            ARCHIVED_FRAGMENT_INDEX -> "ARCHIVED"
            else -> null
        }
    }
}
