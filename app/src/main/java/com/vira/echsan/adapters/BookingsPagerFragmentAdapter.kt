package com.vira.echsan.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.vira.echsan.ui.fragments.bookings.BookingsChildFragment

const val COMPLETED_FRAGMENT_INDEX = 0
const val PROCESS_FRAGMENT_INDEX = 1
const val CANCELED_FRAGMENT_INDEX = 2

class BookingsPagerFragmentAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        COMPLETED_FRAGMENT_INDEX to {BookingsChildFragment.newInstance(args = Bundle().apply {
            putInt(BookingsChildFragment.POSITION_KEY, COMPLETED_FRAGMENT_INDEX)
        })},
        PROCESS_FRAGMENT_INDEX to {BookingsChildFragment.newInstance(args = Bundle().apply {
            putInt(BookingsChildFragment.POSITION_KEY, PROCESS_FRAGMENT_INDEX)
        })},
        CANCELED_FRAGMENT_INDEX to {BookingsChildFragment.newInstance(args = Bundle().apply {
            putInt(BookingsChildFragment.POSITION_KEY, CANCELED_FRAGMENT_INDEX)
        })}
    )

    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}