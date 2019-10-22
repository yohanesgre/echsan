package com.vira.echsan.ui.fragment.umroh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vira.echsan.R
import com.vira.echsan.model.umroh.PaketDetilModel
import kotlinx.android.synthetic.main.fragment_umroh_paket_detil.view.*

class UmrohPaketDetilFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_umroh_paket_detil, container, false)
        val args = arguments?.getParcelable(ARG_NAME) as PaketDetilModel
        view.tv_umroh_paket_detil_travel.text = args.travel
        view.tv_umroh_paket_detil_travel_kota.text = args.lokasiTravel
        view.tv_umroh_paket_detil_keberangkatan.text = args.tglBerangkat
        view.tv_umroh_paket_detil_kepulangan.text = args.tglPulang
        view.tv_umroh_paket_detil_durasi.text = args.durasi
        view.tv_umroh_paket_detil_lokasi.text = args.lokasi
        view.tv_umroh_paket_detil_kuota.text = args.kuota.toString()
        view.tv_umroh_paket_detil_point.text = args.point.toString()
        return view
    }

    companion object{

        const val ARG_NAME = "PaketDetilModel"

        fun newInstance(model:PaketDetilModel):UmrohPaketDetilFragment{
            val fragment = UmrohPaketDetilFragment()
            val args = Bundle().apply { putParcelable(ARG_NAME, model) }
            fragment.arguments = args
            return fragment
        }
    }
}