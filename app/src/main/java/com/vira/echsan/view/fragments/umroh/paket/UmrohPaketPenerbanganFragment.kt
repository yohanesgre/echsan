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
import com.vira.echsan.viewmodel.UmrohSharedViewModel

class UmrohPaketPenerbanganFragment : Fragment(){
    lateinit var sharedViewModel: UmrohSharedViewModel
    lateinit var binding: FragmentUmrohPaketPenerbanganBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedViewModel =
            ViewModelProviders.of(requireActivity()).get(UmrohSharedViewModel::class.java)
        binding = FragmentUmrohPaketPenerbanganBinding.inflate(inflater, container, false)
        binding.rvUmrohPaketPenerbangan.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        val adapter = PaketPenerbanganAdapter()
        binding.rvUmrohPaketPenerbangan.adapter = adapter
        /*sharedViewModel.selectedPaket.observe(viewLifecycleOwner){paket->
            val list = listOf(listOf("Keberangkatan", paket.departure_city.city_name, "Jeddah", paket.departure_plane.dep_plane_name),
                listOf("Kepulangan", "Jeddah", paket.departure_city.city_name, paket.return_plane.ret_plane_name))
            adapter.submitList(list)
        }*/
        return binding.root
    }
    companion object{
        fun newInstance(): UmrohPaketPenerbanganFragment {
            return UmrohPaketPenerbanganFragment()
        }
    }
}