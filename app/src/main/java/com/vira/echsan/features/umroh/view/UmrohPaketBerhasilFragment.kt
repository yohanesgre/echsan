package com.vira.echsan.features.umroh.view

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.os.Build
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
import com.vira.echsan.databinding.FragmentUmrohCheckoutBerhasilBinding
import com.vira.echsan.di.Injectable
import com.vira.echsan.di.injectViewModel
import com.vira.echsan.features.umroh.viewmodel.PaketBerhasilViewModel
import com.vira.echsan.features.umroh.viewmodel.UmrohSharedViewModel
import com.vira.echsan.utils.CalendarHelper
import javax.inject.Inject

class UmrohPaketBerhasilFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: PaketBerhasilViewModel
    private lateinit var sharedViewModel: UmrohSharedViewModel
    private lateinit var binding: FragmentUmrohCheckoutBerhasilBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = injectViewModel(viewModelFactory)
        sharedViewModel =
            ViewModelProviders.of(requireActivity()).get(UmrohSharedViewModel::class.java)
        binding = FragmentUmrohCheckoutBerhasilBinding.inflate(inflater, container, false)
        context ?: return binding.root
        initUI()
        subscribeUI()
        return binding.root
    }

    @TargetApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    private fun subscribeUI(){
        sharedViewModel.FinalPayment.observe(viewLifecycleOwner) { paymentResp ->
            sharedViewModel.CurrentTransaction.observe(viewLifecycleOwner) { trx ->
                sharedViewModel.SelectedPaket.observe(viewLifecycleOwner) { paket ->
                    binding.outputNomorTransaksi.text = trx.transactionCode
                    binding.outputKeberangankatan.text =
                        CalendarHelper.convertDateStringToLocalFormat(paket.date_of_departure)
                    binding.outputKepulangan.text =
                        CalendarHelper.convertDateStringToLocalFormat(paket.date_of_departure)
                    binding.outputDurasi.text = "${paket.day_amount} hari"
                    binding.outputJumlahJamaah.text = "${trx.peopleAmount} jamaah"
                    binding.outputTotalHarga.text = paymentResp.paid.toString()
                }
            }
        }
    }

    private fun initUI(){
        binding.toolbar.title = "Transaksi Berhasil"
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayShowHomeEnabled(true)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        requireActivity().onBackPressedDispatcher.addCallback(this@UmrohPaketBerhasilFragment) {
            requireActivity().finish()
        }
    }
}