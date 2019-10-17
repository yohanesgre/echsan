package com.vira.echsan.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.vira.echsan.R
import com.vira.echsan.adapter.CarouselAdapter
import com.vira.echsan.adapter.CarouselPromoAdapter
import com.vira.echsan.ui.activity.umroh_haji.UmrohActivity
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : androidx.fragment.app.Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        val view:View = inflater.inflate(R.layout.fragment_home, container, false)

        val posts:ArrayList<String> = ArrayList()
        for(i in 1..100){
            posts.add("Testing Content #$i")
        }

        view.rv_carousel.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        view.rv_carousel.adapter = CarouselAdapter(posts)

        view.rv_carousel_promo.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        view.rv_carousel_promo.adapter = CarouselPromoAdapter(posts)

        val snapHelper: SnapHelper = PagerSnapHelper()
        val snapHelperPromo: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(view.rv_carousel)
        snapHelperPromo.attachToRecyclerView(view.rv_carousel_promo)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_home_umroh_haji.setOnClickListener {
            val intent = Intent(activity, UmrohActivity::class.java)
            activity!!.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true;
    }
    companion object {
        fun newInstance() : HomeFragment = HomeFragment()
    }
}