package com.vira.echsan.features.social.adapter

interface CardAdapter {
    var MAX_ELEVATION_FACTOR: Float
        get() = 8f
        set(value) = TODO()
    var BASE_ELEVATION: Float
        get() = 4f
        set(value) = TODO()

    fun getCardViewAt(position: Int)
    fun getCount(): Int
}