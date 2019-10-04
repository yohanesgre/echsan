package com.vira.echsan.ui.activity.hajiumroh

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import android.view.View
import com.vira.echsan.R
import com.vira.echsan.adapter.ImageAdapter
import kotlinx.android.synthetic.main.xml_recycler.*

class PaketLintasGroupActivity : AppCompatActivity() {
    private lateinit var adapter: ImageAdapter
    private var mList: ArrayList<Int> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paket_lintas_group)
        initRecycler()
        init()
    }

    private fun init() {
        mList.add(R.drawable.bg_lintas_group_one)
        mList.add(R.drawable.bg_lintas_group_two)
        mList.add(R.drawable.bg_lintas_group_three)
        mList.add(R.drawable.bg_lintas_group_four)
        mList.add(R.drawable.bg_lintas_group_five)
        mList.add(R.drawable.bg_lintas_group_six)
        mList.add(R.drawable.bg_lintas_group_seven)
        mList.add(R.drawable.bg_lintas_group_eight)

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
