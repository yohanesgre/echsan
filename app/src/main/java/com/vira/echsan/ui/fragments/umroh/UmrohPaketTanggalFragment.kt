package com.vira.echsan.ui.fragments.umroh

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vira.echsan.R

class UmrohPaketTanggalFragment : Fragment(){

    private lateinit var listener: OnTanggalSelected

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnTanggalSelected)
            listener = context
        else
            throw ClassCastException(context.toString() + "must implement OnTanggalSelected.")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_umroh_paket_pilih_tanggal, container, false)
    }

    companion object{
        fun newInstance():UmrohPaketTanggalFragment{
            return UmrohPaketTanggalFragment()
        }
    }

    interface OnTanggalSelected {
        fun onTanggalSelected(tanggal:Int)
    }
}