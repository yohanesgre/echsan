package com.vira.echsan.view.fragments.umroh.paket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.vira.echsan.adapters.umroh.PaketRencanaPerjalananAdapter
import com.vira.echsan.databinding.FragmentUmrohPaketRencanaPerjalananBinding
import com.vira.echsan.viewmodel.UmrohSharedViewModel

class UmrohPaketRencanaPerjalananFragment : Fragment(){
    private lateinit var sharedViewModel: UmrohSharedViewModel
    private lateinit var binding: FragmentUmrohPaketRencanaPerjalananBinding
    private lateinit var adapter:PaketRencanaPerjalananAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedViewModel =
            ViewModelProviders.of(requireActivity()).get(UmrohSharedViewModel::class.java)
        binding = FragmentUmrohPaketRencanaPerjalananBinding.inflate(inflater, container, false)
        binding.apply {
            setOnClick {
                UmrohPaketRencanaPerjalananDialog.display(requireFragmentManager())
            }
        }
        initUI()
        return binding.root
    }

    private fun initUI(){
        binding.rvRencanaPerjalanan.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        adapter = PaketRencanaPerjalananAdapter()
        adapter.submitList(sharedViewModel.listOfRencanaPerjalanan)
        binding.rvRencanaPerjalanan.adapter = adapter
    }

    companion object{
        fun newInstance(): UmrohPaketRencanaPerjalananFragment {
            return UmrohPaketRencanaPerjalananFragment()
        }
    }
}