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
import androidx.navigation.findNavController
import com.vira.echsan.databinding.FragmentUmrohCheckoutPembayaran3Binding
import com.vira.echsan.di.Injectable
import com.vira.echsan.di.injectViewModel
import com.vira.echsan.viewmodel.PaketPembayaran3ViewModel
import com.vira.echsan.viewmodel.UmrohSharedViewModel
import javax.inject.Inject

class UmrohPaketPembayaran3Fragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var pembayaran3ViewModel: PaketPembayaran3ViewModel
    private lateinit var sharedViewModel: UmrohSharedViewModel
    private lateinit var binding: FragmentUmrohCheckoutPembayaran3Binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pembayaran3ViewModel = injectViewModel(viewModelFactory)
        sharedViewModel =
            ViewModelProviders.of(requireActivity()).get(UmrohSharedViewModel::class.java)
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
            val nav =
                UmrohPaketPembayaran3FragmentDirections.actionFragmentUmrohPaketPembayaran3ToFragmentUmrohPaketPembayaran2()
            binding.root.findNavController().navigate(nav)
        }
    }
}