package com.vira.echsan.view.fragments.umroh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import com.vira.echsan.R
import com.vira.echsan.databinding.FragmentUmrohCheckoutPembayaran3Binding
import com.vira.echsan.view.fragments.umroh.pembayaran.UmrohPembayaran2HargaFragment
import com.vira.echsan.viewmodel.PaketUmrohSharedViewModel
import javax.inject.Inject

class UmrohPaketPembayaran3Fragment : Fragment(){
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var sharedViewModel: PaketUmrohSharedViewModel
    private lateinit var binding: FragmentUmrohCheckoutPembayaran3Binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedViewModel =
            ViewModelProviders.of(requireActivity()).get(PaketUmrohSharedViewModel::class.java)
        binding = FragmentUmrohCheckoutPembayaran3Binding.inflate(inflater, container, false).apply {
            setOnClickSudahBayar {
                val nav =
                    UmrohPaketPembayaran3FragmentDirections.actionFragmentUmrohPaketPembayaran3ToFragmentUmrohPaketBerhasil()
                binding.root.findNavController().navigate(nav)
            }
        }
        context ?: return binding.root
        initUI()
        subscribeUI()
        return binding.root
    }

    private fun subscribeUI(){

    }

    private fun initUI(){
        binding.toolbar.title = "PEMBAYARAN"
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayShowHomeEnabled(true)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding.stateCheckout.setStateDescriptionData(sharedViewModel.progressCheckoutDesc)
        requireActivity().onBackPressedDispatcher.addCallback(this@UmrohPaketPembayaran3Fragment){
            sharedViewModel.searchPaket.observe(viewLifecycleOwner){
                val nav =
                    UmrohPaketPembayaran3FragmentDirections.actionFragmentUmrohPaketPembayaran3ToFragmentUmrohPaketPembayaran2()
                binding.root.findNavController().navigate(nav)
            }
        }
    }
}