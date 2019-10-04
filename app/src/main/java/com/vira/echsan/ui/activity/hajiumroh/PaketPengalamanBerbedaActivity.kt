package com.vira.echsan.ui.activity.hajiumroh

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import com.vira.echsan.R
import com.vira.echsan.adapter.ImageAdapter
import kotlinx.android.synthetic.main.xml_recycler.*

class PaketPengalamanBerbedaActivity : AppCompatActivity() {
    private lateinit var adapter: ImageAdapter
    private var mList: ArrayList<Int> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paket_pengalaman_berbeda)
        initRecycler()
        init()
    }

    private fun init() {
        mList.add(R.drawable.bg_pengalaman_berbeda_one)
        mList.add(R.drawable.bg_pengalaman_berbeda_two)
        mList.add(R.drawable.bg_pengalaman_berbeda_three)
        mList.add(R.drawable.bg_pengalaman_berbeda_four)

        adapter.updateData(mList)
        adapter.notifyDataSetChanged()
    }


    private fun initRecycler() {
        recycler.setHasFixedSize(true)
        val mLayoutManager = LinearLayoutManager(this)
        recycler.setLayoutManager(mLayoutManager)
        adapter = ImageAdapter(this)
        recycler.adapter = adapter
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
