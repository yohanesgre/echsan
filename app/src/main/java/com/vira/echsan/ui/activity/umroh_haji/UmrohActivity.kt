package com.vira.echsan.ui.activity.umroh_haji

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.vira.echsan.R

class UmrohActivity : AppCompatActivity(){
    lateinit var toolbar:ActionBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_umroh_paket)
        toolbar = supportActionBar!!
        toolbar.title = getString(R.string.tv_home_umroh_haji)
    }
}