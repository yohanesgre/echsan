package com.vira.echsan.ui.carousel

class CardItem {
    var mTextResource: Int = 0
    var mTitleResource: Int = 0

    constructor(title:Int, text:Int){
        mTextResource = text
        mTitleResource = title
    }

    fun getText():Int{
        return mTextResource
    }

    fun getTitle():Int{
        return mTitleResource
    }
}