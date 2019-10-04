package com.vira.echsan.ui.activity.ebenefit

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.vira.echsan.R

class EGiftActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ebenefit_egift)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
