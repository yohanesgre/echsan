package com.vira.echsan.view.fragments.umroh.paket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.vira.echsan.adapters.umroh.PaketPenerbanganAdapter
import com.vira.echsan.databinding.FragmentUmrohPaketPenerbanganBinding
import com.vira.echsan.viewmodel.PaketUmrohSharedViewModel

class UmrohPaketPenerbanganFragment : Fragment(){
    lateinit var sharedViewModel: PaketUmrohSharedViewModel
    lateinit var binding: FragmentUmrohPaketPenerbanganBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedViewModel = ViewModelProviders.of(requireActivity()).get(PaketUmrohSharedViewModel::class.java)
        binding = FragmentUmrohPaketPenerbanganBinding.inflate(inflater, container, false)
        binding.rvUmrohPaketPenerbangan.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        /*sharedViewModel.selectedPaket.observe(viewLifecycleOwner){paket->

        }*/
        val list = listOf(listOf("Keberangkatan", "Surabaya", "Jeddah", "Garuda"),
            listOf("Kepulangan", "Jeddah", "Surabaya", "Garuda"))
        val adapter = PaketPenerbanganAdapter()
        adapter.submitList(list)
        binding.rvUmrohPaketPenerbangan.adapter = adapter
        return binding.root
    }
    companion object{
        fun newInstance(): UmrohPaketPenerbanganFragment {
            return UmrohPaketPenerbanganFragment()
        }
    }
}