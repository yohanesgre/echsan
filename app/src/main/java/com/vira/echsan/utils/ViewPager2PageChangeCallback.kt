package com.vira.echsan.utils

import androidx.viewpager2.widget.ViewPager2

class ViewPager2PageChangeCallback(private val listener: (Int) -> Unit) :
    ViewPager2.OnPageChangeCallback() {

    override fun onPageSelected(position: Int) {
        super.onPageSelected(position)
        when (position) {
            0 -> listener.invoke(1)
            2 -> listener.invoke(1)
        }
    }
}