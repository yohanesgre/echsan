package com.vira.echsan.ui.activity.umroh_haji

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.vira.echsan.R
import com.vira.echsan.adapter.CarouselPromoAdapter
import kotlinx.android.synthetic.main.activity_umroh.*

class UmrohActivity : AppCompatActivity(){
    lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_umroh)
        toolbar = findViewById(R.id.toolbar)
        toolbar.title = getString(R.string.tv_home_umroh_haji)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val posts:ArrayList<String> = ArrayList()
        for(i in 1..100){
            posts.add("Testing Content #$i")
        }

        rv_carousel_recommendations.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_carousel_recommendations.adapter = CarouselPromoAdapter(posts)

        val snapHelper:SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(rv_carousel_recommendations)


        btn_umroh_cari.setOnClickListener{
            val intent = Intent(this, PaketUmrohActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}