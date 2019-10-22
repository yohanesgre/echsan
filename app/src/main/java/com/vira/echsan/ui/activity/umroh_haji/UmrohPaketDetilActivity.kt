package com.vira.echsan.ui.activity.umroh_haji

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.vira.echsan.R
import com.vira.echsan.model.umroh.PaketDetilModel
import com.vira.echsan.ui.fragment.umroh.UmrohPaketDetilFragment
import com.vira.echsan.ui.fragment.umroh.UmrohPaketTanggalFragment
import kotlinx.android.synthetic.main.activity_umroh_paket_detil.*

class UmrohPaketDetilActivity : AppCompatActivity(){

    var toolbar: Toolbar? = null
    var collapsingToolbarLayout: CollapsingToolbarLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_umroh_paket_detil)
        toolbar = collapsing_toolbar
        collapsingToolbarLayout = collapsing_toolbar_layout
        setSupportActionBar(toolbar)
        collapsingToolbarLayout!!.title = "Umroh Paket"
        collapsingToolbarLayout!!.setExpandedTitleColor(
            ContextCompat.getColor(this, R.color.colorPrimary)
        )
        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .add(R.id.container_fragment_detil, UmrohPaketDetilFragment.newInstance(initModelDetil()))
                .add(R.id.container_fragment_pilih_tanggal, UmrohPaketTanggalFragment.newInstance())
                .commit()
        }
    }

    fun initModelDetil() : PaketDetilModel{
        return PaketDetilModel("Lintas Darfiq", "Surabaya", "1 Oktober 2019", "11 Oktober 2019",
        "10 hari", "Hotel", 20, 2000)
    }
}