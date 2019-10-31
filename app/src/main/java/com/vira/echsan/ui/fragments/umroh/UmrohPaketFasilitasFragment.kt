package com.vira.echsan.ui.fragments.umroh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.vira.echsan.adapters.PaketFasilitasAdapter
import com.vira.echsan.databinding.FragmentUmrohPaketFasilitasBinding
import com.vira.echsan.viewmodel.PaketUmrohSharedViewModel

class UmrohPaketFasilitasFragment : Fragment(){
    lateinit var sharedViewModel: PaketUmrohSharedViewModel
    lateinit var binding: FragmentUmrohPaketFasilitasBinding

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
        return binding.root
    }
    companion object{
        fun newInstance():UmrohPaketFasilitasFragment{
            return UmrohPaketFasilitasFragment()
        }
    }
}