package com.vira.echsan.view.fragments.umroh.paket

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.vira.echsan.adapters.umroh.PaketFasilitasNewAdapter
import com.vira.echsan.databinding.FragmentUmrohPaketFasilitasNewBinding
import com.vira.echsan.viewmodel.UmrohSharedViewModel

class UmrohPaketFasilitasFragment : Fragment(){
    lateinit var sharedViewModel: UmrohSharedViewModel
    lateinit var binding: FragmentUmrohPaketFasilitasNewBinding

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedViewModel =
            ViewModelProviders.of(requireActivity()).get(UmrohSharedViewModel::class.java)
        binding = FragmentUmrohPaketFasilitasNewBinding.inflate(inflater, container, false)
        val adapter = PaketFasilitasNewAdapter()
        adapter.submitList(sharedViewModel.listOfFasilitas)
        binding.rvUmrohPaketFasilitas.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvUmrohPaketFasilitas.adapter = adapter
        /* Old View
        binding.rvUmrohPaketFasilitas.layoutManager = GridLayoutManager(requireContext(), 4, GridLayoutManager.VERTICAL, false)
        binding.rvUmrohPaketFasilitas.adapter = adapter
        binding.layoutFasilitas.showButton = true
        binding.layoutFasilitas.showShadow = true
        binding.layoutFasilitas.animationDuration = 300
        binding.layoutFasilitas.collapsedHeight = 250
        binding.layoutFasilitas.moreText = "Lihat Semua Fasilitas"
        binding.layoutFasilitas.moreColor = requireActivity().getColor(R.color.colorPrimary)*/
        return binding.root
    }
    companion object{
        fun newInstance(): UmrohPaketFasilitasFragment {
            return UmrohPaketFasilitasFragment()
        }
    }
}