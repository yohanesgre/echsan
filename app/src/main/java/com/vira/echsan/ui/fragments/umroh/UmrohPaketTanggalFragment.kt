package com.vira.echsan.ui.fragments.umroh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import com.vira.echsan.R
import com.vira.echsan.adapters.PaketTanggalAdapter
import com.vira.echsan.databinding.FragmentUmrohPaketPilihTanggalBinding
import com.vira.echsan.viewmodel.PaketUmrohSharedViewModel

class UmrohPaketTanggalFragment : Fragment(){

    lateinit var sharedViewModel: PaketUmrohSharedViewModel
    lateinit var binding:FragmentUmrohPaketPilihTanggalBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedViewModel = ViewModelProviders.of(requireActivity()).get(PaketUmrohSharedViewModel::class.java)
        binding = FragmentUmrohPaketPilihTanggalBinding.inflate(inflater, container, false)
        initUI()
        return binding.root
    }

    private fun initUI(){
        binding.rvUmrohPaketPilihTanggal.layoutManager = GridLayoutManager(requireContext(), 5, GridLayoutManager.VERTICAL, false)
        val adapter = PaketTanggalAdapter(requireActivity(), sharedViewModel)
        adapter.submitList(sharedViewModel.listOfTanggal)
        binding.rvUmrohPaketPilihTanggal.adapter = adapter
        subscribeUI()
    }

    private fun subscribeUI(){
        sharedViewModel.selectedTanggal.observe(viewLifecycleOwner){
            println("Selected tanggal: $it")
        }
    }

    companion object{
        fun newInstance():UmrohPaketTanggalFragment{
            return UmrohPaketTanggalFragment()
        }
    }
}