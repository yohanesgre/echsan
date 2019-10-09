package com.vira.echsan.ui.activity

import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import com.vira.echsan.R
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.xml_toolbar.*

class UmrohHajiActivity : AppCompatActivity(){
    lateinit var toolbar:ActionBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_umrohhaji)
        toolbar = supportActionBar!!
        toolbar.title = getString(R.string.tv_home_umroh_haji)
    }
}