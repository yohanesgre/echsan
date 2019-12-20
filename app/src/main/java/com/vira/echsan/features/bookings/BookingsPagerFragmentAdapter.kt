package com.vira.echsan.features.bookings

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.vira.echsan.features.bookings.view.BookingsChildFragment

const val ACTIVE_FRAGMENT_INDEX = 1
const val ARCHIVED_FRAGMENT_INDEX = 0

class BookingsPagerFragmentAdapter(fragment: Fragment, private val userId: Int) :
    FragmentStateAdapter(fragment) {
    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        ACTIVE_FRAGMENT_INDEX to {
            BookingsChildFragment().apply {
                arguments = Bundle().apply {
                    putInt(
                        BookingsChildFragment.POSITION_KEY,
                        ARCHIVED_FRAGMENT_INDEX
                    )
                    putInt("UserID", userId)
                }
            }
        },
        ARCHIVED_FRAGMENT_INDEX to {
            BookingsChildFragment().apply {
                arguments = Bundle().apply {
                    putInt(
                        BookingsChildFragment.POSITION_KEY,
                        ACTIVE_FRAGMENT_INDEX
                    )
                    putInt("UserID", userId)
                }
            }
        }
    )

    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}