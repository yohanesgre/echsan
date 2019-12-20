package com.vira.echsan.features.umroh.view

import android.annotation.SuppressLint
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
import com.vira.echsan.adapters.pembayaran.MetodePembayaranAdapter
import com.vira.echsan.adapters.pembayaran.TipePembayaranParentAdapter
import com.vira.echsan.databinding.FragmentUmrohCheckoutPembayaranBinding
import com.vira.echsan.di.Injectable
import com.vira.echsan.di.injectViewModel
import com.vira.echsan.features.umroh.viewmodel.PaketPembayaranViewModel
import com.vira.echsan.features.umroh.viewmodel.UmrohSharedViewModel
import javax.inject.Inject

class UmrohPaketPembayaranFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: PaketPembayaranViewModel
    private lateinit var sharedViewModel: UmrohSharedViewModel
    private lateinit var binding: FragmentUmrohCheckoutPembayaranBinding
    private val adapterTipePembayaran by lazy { TipePembayaranParentAdapter() }
    private lateinit var adapterMetodePembayaran:MetodePembayaranAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = injectViewModel(viewModelFactory)
        sharedViewModel =
            ViewModelProviders.of(requireActivity()).get(UmrohSharedViewModel::class.java)
        binding = FragmentUmrohCheckoutPembayaranBinding.inflate(inflater, container, false).apply {
            this.setOnClick {
                val nav =
                    UmrohPaketPembayaranFragmentDirections.actionFragmentUmrohPaketPembayaranToFragmentUmrohPaketPembayaran2()
                this.root.findNavController().navigate(nav)
            }
        }
        context ?: return binding.root
        initUI()
        subscribeUI()
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun subscribeUI(){
        sharedViewModel.SelectedPaket.observe(viewLifecycleOwner) {
            binding.tvJadwalKeberangkatan.text = it.date_of_departure
            binding.tvDurasi.text = it.day_amount.toString()
            binding.tvHotel.text = "${it.makkah_hotel} | ${it.madinah_hotel}"
            binding.tvPenerbangan.text =
                "${it.departure_plane.dep_plane_name} | ${it.return_plane.ret_plane_name}"
            binding.tvHarga.text = it.price
        }
    }

    private fun initUI(){
        binding.toolbar.title = "PEMBAYARAN"
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayShowHomeEnabled(true)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding.stateCheckout.setStateDescriptionData(sharedViewModel.progressCheckoutDesc)
        requireActivity().onBackPressedDispatcher.addCallback(this@UmrohPaketPembayaranFragment){
            val nav =
                UmrohPaketPembayaranFragmentDirections.actionFragmentUmrohPaketPembayaranToFragmentUmrohPaketPemesanan()
            binding.root.findNavController().navigate(nav)

        }
        binding.rvTipePembayaran.adapter = adapterTipePembayaran
        adapterTipePembayaran.addSectionItem(viewModel.tipePembayaranSection)
        adapterTipePembayaran.addSharedViewModel(sharedViewModel)

        adapterMetodePembayaran = MetodePembayaranAdapter(viewModel.metodePembayaran)
        binding.rvMetodePembayaran.adapter = adapterMetodePembayaran
    }
}