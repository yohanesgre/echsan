package com.vira.echsan.util

import android.os.Bundle
import android.support.v4.app.FragmentTabHost
import com.vira.echsan.ui.fragment.BookingsChildFragment

fun FragmentTabHost.addPositionTab(position: Int) {
    val arg = Bundle().apply { putInt(BookingsChildFragment.POSITION_KEY, position) }
    val klass = BookingsChildFragment::class.java
    when(position){
        BookingsChildFragment.TAB_COMPLETED -> this.addTab(this.newTabSpec("Completed").setIndicator("Completed"), klass, arg)
        BookingsChildFragment.TAB_PROCESS -> this.addTab(this.newTabSpec("Process").setIndicator("Completed"), klass, arg)
        BookingsChildFragment.TAB_CANCELED -> this.addTab(this.newTabSpec("Canceled").setIndicator("Canceled"), klass, arg)
    }

}
