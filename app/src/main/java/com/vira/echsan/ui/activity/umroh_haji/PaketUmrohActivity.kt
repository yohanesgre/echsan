package com.vira.echsan.ui.activity.umroh_haji

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.vira.echsan.R
import com.vira.echsan.adapter.PaketUmrohAdapter
import com.vira.echsan.model.recyclerview.PaketUmrohModel
import kotlinx.android.synthetic.main.activity_umroh.*
import kotlinx.android.synthetic.main.activity_umroh_paket.*

class PaketUmrohActivity : AppCompatActivity(){
    lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_umroh_paket)
        toolbar = findViewById(R.id.toolbar)
        toolbar.title = "Paket Umroh"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val posts:ArrayList<PaketUmrohModel> = ArrayList()
        posts.add(PaketUmrohModel("Umroh Special", "Oktober 2020", "9 hari",
            "Hotel 1", "Airlines", "Lintas Darfiq", "Rp. 25.000.000"))
        posts.add(PaketUmrohModel("Umroh Special", "November 2020", "10 hari",
            "Hotel 2", "Airlines", "Lintas Darfiq", "Rp. 26.000.000"))
        posts.add(PaketUmrohModel("Umroh Special", "Desember 2020", "11 hari",
            "Hotel 3", "Airlines", "Lintas Darfiq", "Rp. 27.000.000"))
        posts.add(PaketUmrohModel("Umroh Special", "Oktober 2020", "9 hari",
            "Hotel 1", "Airlines", "Lintas Darfiq", "Rp. 25.000.000"))
        posts.add(PaketUmrohModel("Umroh Special", "November 2020", "10 hari",
            "Hotel 2", "Airlines", "Lintas Darfiq", "Rp. 26.000.000"))
        posts.add(PaketUmrohModel("Umroh Special", "Desember 2020", "11 hari",
            "Hotel 3", "Airlines", "Lintas Darfiq", "Rp. 27.000.000"))
        posts.add(PaketUmrohModel("Umroh Special", "Oktober 2020", "9 hari",
            "Hotel 1", "Airlines", "Lintas Darfiq", "Rp. 25.000.000"))
        posts.add(PaketUmrohModel("Umroh Special", "November 2020", "10 hari",
            "Hotel 2", "Airlines", "Lintas Darfiq", "Rp. 26.000.000"))
        posts.add(PaketUmrohModel("Umroh Special", "Desember 2020", "11 hari",
            "Hotel 3", "Airlines", "Lintas Darfiq", "Rp. 27.000.000"))

        rv_umroh_paket.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_umroh_paket.adapter = PaketUmrohAdapter(posts)

        fab_umroh_paket_filter.setOnClickListener {
            val intent:Intent = Intent(this, PaketUmrohFilterActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}