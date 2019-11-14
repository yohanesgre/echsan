package com.vira.echsan.utils

import android.content.Context
import android.util.DisplayMetrics


fun DpToPx(context: Context, dp: Int): Int {
    val displayMetrics = context.getResources().getDisplayMetrics()
    return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
}