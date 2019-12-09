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
import com.vira.echsan.adapters.pemesanan.PemesananDataJamaahAdapter
import com.vira.echsan.databinding.FragmentUmrohCheckoutPemesananBinding
import com.vira.echsan.di.Injectable
import com.vira.echsan.di.injectViewModel
import com.vira.echsan.viewmodel.PaketPemesananViewModel
import com.vira.echsan.viewmodel.UmrohSharedViewModel
import javax.inject.Inject

class UmrohPaketPemesananFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var pemesananViewModel: PaketPemesananViewModel
    private lateinit var sharedViewModel: UmrohSharedViewModel
    private lateinit var binding: FragmentUmrohCheckoutPemesananBinding
    private lateinit var adapter:PemesananDataJamaahAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        pemesananViewModel = injectViewModel(viewModelFactory)
        sharedViewModel =
            ViewModelProviders.of(requireActivity()).get(UmrohSharedViewModel::class.java)
        binding = FragmentUmrohCheckoutPemesananBinding.inflate(inflater, container, false).apply{
            this.setOnClickLanjut {
                val nav =
                    UmrohPaketPemesananFragmentDirections.actionFragmentUmrohPaketPemesananToFragmentUmrohPaketPembayaran()
                this.root.findNavController().navigate(nav)
            }
            this.setOnClickTambah{
                if (pemesananViewModel.jumlahJamaah.value!! < 5)
                    pemesananViewModel.addJumlahJamaah()
            }
            this.setOnClickKurang {
                if (pemesananViewModel.jumlahJamaah.value!! > 0)
                    pemesananViewModel.minusJumlahJamaah()
            }
        }
        context ?: return binding.root
        initUI()
        subscribeUI()
        return binding.root
    }

    private fun subscribeUI(){
        pemesananViewModel.jumlahJamaah.observe(viewLifecycleOwner) { jumlah ->
            binding.jumlahJamaah = jumlah.toString()
            var listJamaah = mutableListOf<String>()
            (1..jumlah).forEach { i ->
                listJamaah.add("Data Jamaah $i")
            }
            adapter.itemList = listJamaah
        }
    }

    private fun initUI(){
        binding.toolbar.title = "Pemesanan"
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayShowHomeEnabled(true)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding.stateCheckout.setStateDescriptionData(sharedViewModel.progressCheckoutDesc)
        requireActivity().onBackPressedDispatcher.addCallback(this@UmrohPaketPemesananFragment){
            val nav =
                UmrohPaketPemesananFragmentDirections.actionFragmentUmrohPaketPemesananToFragmentUmrohPaket()
            binding.root.findNavController().navigate(nav)
        }
        adapter = PemesananDataJamaahAdapter()
        binding.rvDataJamaah.adapter = adapter
    }
}