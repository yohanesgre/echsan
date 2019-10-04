package com.vira.echsan.ui.activity.ebenefit

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import com.vira.echsan.R
import kotlinx.android.synthetic.main.activity_ebenefit.*

class EBenefitActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ebenefit)
        containerEVoucher.setOnClickListener(this)
        containerEGift.setOnClickListener(this)
        containerEPoint.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.containerEGift -> {
                val intent = Intent(this, EGiftActivity::class.java)
                startActivity(intent)
            }
            R.id.containerEPoint -> {
                val intent = Intent(this, EPointActivity::class.java)
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
