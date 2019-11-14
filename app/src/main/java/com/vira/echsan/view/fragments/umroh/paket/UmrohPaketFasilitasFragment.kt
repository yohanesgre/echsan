package com.vira.echsan.view.fragments.umroh.paket

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.vira.echsan.R
import com.vira.echsan.adapters.umroh.PaketFasilitasAdapter
import com.vira.echsan.databinding.FragmentUmrohPaketFasilitasBinding
import com.vira.echsan.viewmodel.PaketUmrohSharedViewModel

class UmrohPaketFasilitasFragment : Fragment(){
    lateinit var sharedViewModel: PaketUmrohSharedViewModel
    lateinit var binding: FragmentUmrohPaketFasilitasBinding

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedViewModel = ViewModelProviders.of(requireActivity()).get(PaketUmrohSharedViewModel::class.java)
        binding = FragmentUmrohPaketFasilitasBinding.inflate(inflater, container, false)
        val adapter = PaketFasilitasAdapter()
        adapter.submitList(sharedViewModel.listOfFasilitas)
        binding.rvUmrohPaketFasilitas.layoutManager = GridLayoutManager(requireContext(), 4, GridLayoutManager.VERTICAL, false)
        binding.rvUmrohPaketFasilitas.adapter = adapter
        binding.layoutFasilitas.showButton = true
        binding.layoutFasilitas.showShadow = true
        binding.layoutFasilitas.animationDuration = 300
        binding.layoutFasilitas.collapsedHeight = 250
        binding.layoutFasilitas.moreText = "Lihat Semua Fasilitas"
        binding.layoutFasilitas.moreColor = requireActivity().getColor(R.color.colorPrimary)
        return binding.root
    }
    companion object{
        fun newInstance(): UmrohPaketFasilitasFragment {
            return UmrohPaketFasilitasFragment()
        }
    }
}