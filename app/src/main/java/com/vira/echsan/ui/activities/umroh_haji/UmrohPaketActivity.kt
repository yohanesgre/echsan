package com.vira.echsan.ui.activities.umroh_haji

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.vira.echsan.R
import com.vira.echsan.ui.fragments.umroh.UmrohPaketFilterDialog
import kotlinx.android.synthetic.main.fragment_umroh_paket.*

class UmrohPaketActivity : AppCompatActivity(){
    lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_umroh_paket)
        toolbar = findViewById(R.id.toolbar)
        toolbar.title = "Paket Umroh"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        rv_umroh_paket.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        fab_umroh_paket_filter.setOnClickListener {
            UmrohPaketFilterDialog.display(supportFragmentManager)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}