package com.vira.echsan.ui.activity.esosial

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import com.vira.echsan.R
import kotlinx.android.synthetic.main.activity_esosial_opsi_pembayaran.*

class OpsiPembayaranActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_esosial_opsi_pembayaran)
        containerCash.setOnClickListener(this)
        containerEPoint.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.containerCash -> {
                val intent = Intent(this, CashActivity::class.java)
                startActivity(intent)
            }
            R.id.containerEPoint -> {
                val intent = Intent(this, DonasiRedeemPoinActivity::class.java)
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
