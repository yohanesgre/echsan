package com.vira.echsan.ui.activity.hajiumroh

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import com.vira.echsan.R
import kotlinx.android.synthetic.main.activity_paket.*
import kotlinx.android.synthetic.main.xml_toolbar.*

class PaketActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paket)

        title = "E-Haji & Umroh"
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        containerLintasGroup.setOnClickListener(this)
        containerPengalamanBerbeda.setOnClickListener(this)
        txtHaji.setOnClickListener(this)
        txtUmroh.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.txtUmroh -> {
                val intent = Intent(this, PaketUmrohActivity::class.java)
                startActivity(intent)
            }
            R.id.txtHaji -> {
                val intent = Intent(this, PaketHajiActivity::class.java)
                startActivity(intent)
            }
            R.id.containerLintasGroup -> {
                val intent = Intent(this, PaketLintasGroupActivity::class.java)
                startActivity(intent)
            }
            R.id.containerPengalamanBerbeda -> {
                val intent = Intent(this, PaketPengalamanBerbedaActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
