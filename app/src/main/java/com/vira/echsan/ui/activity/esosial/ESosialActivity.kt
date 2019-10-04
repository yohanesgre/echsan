package com.vira.echsan.ui.activity.esosial

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import com.vira.echsan.ui.activity.HomepageFirstActivity
import com.vira.echsan.R
import kotlinx.android.synthetic.main.activity_esosial.*

class ESosialActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_esosial)
        containerJariyahAsatidz.setOnClickListener(this)
        containerJariyahDhuafa.setOnClickListener(this)
        containerJariyahMasjid.setOnClickListener(this)
        containerJariyahPesantren.setOnClickListener(this)
        containerJariyahYatim.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.containerJariyahAsatidz -> {
                val intent = Intent(this, OpsiPembayaranActivity::class.java)
                startActivity(intent)
            }
            R.id.containerJariyahDhuafa -> {
                val intent = Intent(this, OpsiPembayaranActivity::class.java)
                startActivity(intent)
            }
            R.id.containerJariyahMasjid -> {
                val intent = Intent(this, OpsiPembayaranActivity::class.java)
                startActivity(intent)
            }
            R.id.containerJariyahPesantren -> {
                val intent = Intent(this, OpsiPembayaranActivity::class.java)
                startActivity(intent)
            }
            R.id.containerJariyahYatim -> {
                val intent = Intent(this, OpsiPembayaranActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                val intent = Intent(this, HomepageFirstActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
