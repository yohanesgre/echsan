package com.vira.echsan.ui.carousel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.viewpager.widget.PagerAdapter
import com.vira.echsan.R


class CardPagerAdapter : PagerAdapter(), CardAdapter {

    private val mViews: MutableList<CardView?>
    private val mData: MutableList<CardItem>
    private var mBaseElevation: Float = 0.toFloat()

    init {
        mData = ArrayList()
        mViews = ArrayList()
    }

    fun addCardItem(item: CardItem) {
        mViews.add(null)
        mData.add(item)
    }

    override fun getBaseElevation(): Float {
        return mBaseElevation
    }

    override fun getCardViewAt(position: Int): CardView {
        return mViews[position]!!
    }

    override fun getCount(): Int {
        return mData.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context)
            .inflate(R.layout.adapter, container, false)
        container.addView(view)
        bind(mData[position], view)
        val cardView = view.findViewById(R.id.cardView) as CardView

        if (mBaseElevation == 0f) {
            mBaseElevation = cardView.cardElevation
        }

        cardView.maxCardElevation = mBaseElevation * 8
        mViews[position] = cardView
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
        mViews.set(position, null)
    }

    private fun bind(item: CardItem, view: View) {
        val titleTextView = view.findViewById(R.id.titleTextView) as TextView
        val contentTextView = view.findViewById(R.id.contentTextView) as TextView
        titleTextView.setText(item.getTitle())
        contentTextView.setText(item.getText())
    }

}