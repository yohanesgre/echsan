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
import com.vira.echsan.adapters.pembayaran.TipePembayaranParentAdapter
import com.vira.echsan.databinding.FragmentUmrohCheckoutPembayaran2Binding
import com.vira.echsan.di.Injectable
import com.vira.echsan.di.injectViewModel
import com.vira.echsan.utils.ConvertCurrencyToDouble
import com.vira.echsan.utils.ConvertToCurrency
import com.vira.echsan.viewmodel.PaketPembayaran2ViewModel
import com.vira.echsan.viewmodel.UmrohSharedViewModel
import javax.inject.Inject

class UmrohPaketPembayaran2Fragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var pembayaran2ViewModel: PaketPembayaran2ViewModel
    private lateinit var sharedViewModel: UmrohSharedViewModel
    private lateinit var binding: FragmentUmrohCheckoutPembayaran2Binding
    private val adapter by lazy { TipePembayaranParentAdapter() }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pembayaran2ViewModel = injectViewModel(viewModelFactory)
        sharedViewModel =
            ViewModelProviders.of(requireActivity()).get(UmrohSharedViewModel::class.java)
        binding = FragmentUmrohCheckoutPembayaran2Binding.inflate(inflater, container, false).apply {
            setOnClick {
                val nav =
                    UmrohPaketPembayaran2FragmentDirections.actionFragmentUmrohPaketPembayaran2ToFragmentUmrohPaketPembayaran3()
                binding.root.findNavController().navigate(nav)
            }
        }
        context ?: return binding.root
        initUI()
        subscribeUI()
        return binding.root
    }

    private fun subscribeUI(){
        sharedViewModel.SelectedPaket.observe(viewLifecycleOwner) {
            binding.tvPaketHarga.text = it.price
            binding.tvPaketHargaJamaah.text =
                ConvertToCurrency(ConvertCurrencyToDouble(it.price) * 2, null)
            binding.tvPaketHargaTotal.text =
                ConvertToCurrency(ConvertCurrencyToDouble(it.price) * 2, null)
        }
    }

    private fun initUI(){
        binding.toolbar.title = "PEMBAYARAN"
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayShowHomeEnabled(true)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding.stateCheckout.setStateDescriptionData(sharedViewModel.progressCheckoutDesc)
        requireActivity().onBackPressedDispatcher.addCallback(this@UmrohPaketPembayaran2Fragment){
            val nav =
                UmrohPaketPembayaran2FragmentDirections.actionFragmentUmrohPaketPembayaran2ToFragmentUmrohPaketPembayaran()
            binding.root.findNavController().navigate(nav)
        }
    }

}