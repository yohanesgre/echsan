package com.vira.echsan.ui.fragments.umroh.paket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import com.vira.echsan.databinding.FragmentUmrohPaketDetilBinding
import com.vira.echsan.viewmodel.PaketUmrohSharedViewModel

class UmrohPaketDetilFragment : Fragment(){

    lateinit var sharedViewModel: PaketUmrohSharedViewModel
    lateinit var binding:FragmentUmrohPaketDetilBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedViewModel = ViewModelProviders.of(requireActivity()).get(PaketUmrohSharedViewModel::class.java)
        binding = FragmentUmrohPaketDetilBinding.inflate(inflater, container, false)
        initUI()
        return binding.root
    }

    private fun initUI() {
        sharedViewModel.selectedPaket.observe(viewLifecycleOwner){paket->
            binding.tvUmrohPaketDetilTravel.text = paket.travel
            binding.tvUmrohPaketDetilBulanBerangkat.text = paket.tanggal
            binding.tvUmrohPaketDetilDurasi.text = paket.durasi
            binding.tvUmrohPaketDetilLokasi.text = paket.lokasi
            binding.tvUmrohPaketDetilKuota.text = paket.kuota.toString()
            binding.tvUmrohPaketDetilPoint.text = paket.point.toString()
        }
    }

    companion object{
        fun newInstance(): UmrohPaketDetilFragment {
            return UmrohPaketDetilFragment()
        }
    }
}