package com.vira.echsan.ui.carousel

import androidx.fragment.app.Fragment
import androidx.cardview.widget.CardView
import android.os.Bundle
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.vira.echsan.R


class CardFragment : Fragment(){
    lateinit var mCardView: CardView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_adapter, container, false)
        mCardView = view.findViewById(R.id.cardView)
        mCardView.maxCardElevation = mCardView.cardElevation * 8F
        return view
    }

    fun getCardView() : CardView{
        return mCardView
    }
}