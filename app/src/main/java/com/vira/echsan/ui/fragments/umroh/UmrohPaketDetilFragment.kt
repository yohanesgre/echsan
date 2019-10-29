package com.vira.echsan.ui.fragments.umroh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vira.echsan.R

class UmrohPaketDetilFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_umroh_paket_detil, container, false)
        return view
    }

    companion object{

        const val ARG_NAME = "PaketDetilModel"

        fun newInstance():UmrohPaketDetilFragment{
            val fragment = UmrohPaketDetilFragment()
            return fragment
        }
    }
}