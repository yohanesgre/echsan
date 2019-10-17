package com.vira.echsan.ui.carousel

import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import java.util.*

class CardFragmentPagerAdapter : FragmentStatePagerAdapter, CardAdapter{

    lateinit var mFragments: ArrayList<CardFragment>
    var mBaseElevation: Float = 0F

    constructor(fm: FragmentManager, baseElevation: Float):super(fm) {
        mBaseElevation = baseElevation
        for (x in 0 until 5){
            addCardFragment(CardFragment())
        }
    }

    override fun getBaseElevation(): Float {
        return mBaseElevation
    }

    override fun getCardViewAt(position: Int): CardView {
        return mFragments.get(position).getCardView()
    }

    override fun getCount(): Int {
        return mFragments.size
    }

    override fun getItem(position: Int): Fragment {
        return mFragments.get(position)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Object {
        var fragment: Object = super.instantiateItem(container, position) as Object;
        mFragments.set(position, fragment as CardFragment)
        return fragment
    }

    fun addCardFragment(fragment: CardFragment){
        mFragments.add(fragment)
    }
}

