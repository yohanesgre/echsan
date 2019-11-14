package com.vira.echsan.view.fragments.umroh.paket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.vira.echsan.adapters.umroh.PaketHotelAdapter
import com.vira.echsan.adapters.umroh.PaketPenerbanganAdapter
import com.vira.echsan.databinding.FragmentUmrohPaketHotelBinding
import com.vira.echsan.databinding.FragmentUmrohPaketPenerbanganBinding
import com.vira.echsan.viewmodel.PaketUmrohSharedViewModel

class UmrohPaketHotelFragment : Fragment(){
    lateinit var sharedViewModel: PaketUmrohSharedViewModel
    lateinit var binding: FragmentUmrohPaketHotelBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedViewModel = ViewModelProviders.of(requireActivity()).get(PaketUmrohSharedViewModel::class.java)
        binding = FragmentUmrohPaketHotelBinding.inflate(inflater, container, false)
        binding.rvUmrohPaketHotel.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        /*sharedViewModel.selectedPaket.observe(viewLifecycleOwner){paket->

        }*/
        val list = listOf(listOf("Surabaya", "Hotel"),
            listOf("Jeddah", "Hotel"))
        val adapter = PaketHotelAdapter()
        adapter.submitList(list)
        binding.rvUmrohPaketHotel.adapter = adapter
        return binding.root
    }
    companion object{
        fun newInstance(): UmrohPaketHotelFragment {
            return UmrohPaketHotelFragment()
        }
    }
}