package com.vira.echsan.ui.activities.umroh_haji

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.vira.echsan.R
import kotlinx.android.synthetic.main.fragment_umroh.*

class UmrohActivity : AppCompatActivity(){
    lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_umroh)
        toolbar = findViewById(R.id.toolbar)
        toolbar.title = getString(R.string.tv_home_umroh_haji)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val posts:ArrayList<String> = ArrayList()
        for(i in 1..100){
            posts.add("Testing Content #$i")
        }

        btn_umroh_cari.setOnClickListener{
            val intent = Intent(this, UmrohPaketActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}