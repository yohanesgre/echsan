package com.vira.echsan.ui.carousel

import androidx.cardview.widget.CardView

interface CardAdapter {

    fun getBaseElevation(): Float

    fun getCardViewAt(position: Int): CardView

    fun getCount(): Int

}