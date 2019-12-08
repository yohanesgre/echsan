package com.vira.echsan.view.fragments.umroh.paket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.vira.echsan.adapters.umroh.PaketHotelAdapter
import com.vira.echsan.databinding.FragmentUmrohPaketHotelBinding
import com.vira.echsan.viewmodel.UmrohSharedViewModel

class UmrohPaketHotelFragment : Fragment(){
    lateinit var sharedViewModel: UmrohSharedViewModel
    lateinit var binding: FragmentUmrohPaketHotelBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedViewModel =
            ViewModelProviders.of(requireActivity()).get(UmrohSharedViewModel::class.java)
        binding = FragmentUmrohPaketHotelBinding.inflate(inflater, container, false)
        binding.rvUmrohPaketHotel.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        val adapter = PaketHotelAdapter()
        binding.rvUmrohPaketHotel.adapter = adapter
        /*sharedViewModel.selectedPaket.observe(viewLifecycleOwner){paket->
            val list = listOf(listOf("Makkah", paket.makkah_hotel), listOf("Madinah",paket.madinah_hotel))
            adapter.submitList(list)
        }*/
        return binding.root
    }
    companion object{
        fun newInstance(): UmrohPaketHotelFragment {
            return UmrohPaketHotelFragment()
        }
    }
}