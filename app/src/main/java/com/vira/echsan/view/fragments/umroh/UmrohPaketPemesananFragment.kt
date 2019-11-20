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
import com.vira.echsan.adapters.pemesanan.PemesananDataJamaahAdapter
import com.vira.echsan.databinding.FragmentUmrohCheckoutPemesananBinding
import com.vira.echsan.view.fragments.umroh.pemesanan.UmrohPemesananDataFragment
import com.vira.echsan.viewmodel.PaketUmrohSharedViewModel
import javax.inject.Inject

class UmrohPaketPemesananFragment : Fragment(){
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var sharedViewModel: PaketUmrohSharedViewModel
    private lateinit var binding: FragmentUmrohCheckoutPemesananBinding
    private lateinit var adapter:PemesananDataJamaahAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedViewModel =
            ViewModelProviders.of(requireActivity()).get(PaketUmrohSharedViewModel::class.java)
        binding = FragmentUmrohCheckoutPemesananBinding.inflate(inflater, container, false).apply{
            this.setOnClickLanjut {
                val nav =
                    UmrohPaketPemesananFragmentDirections.actionFragmentUmrohPaketPemesananToFragmentUmrohPaketPembayaran()
                this.root.findNavController().navigate(nav)
            }
            this.setOnClickTambah{
                if (sharedViewModel.jumlahJamaah.value!! < 5)
                    sharedViewModel.addJumlahJamaah()
            }
            this.setOnClickKurang {
                if (sharedViewModel.jumlahJamaah.value!! > 0)
                    sharedViewModel.minusJumlahJamaah()
            }
        }
        context ?: return binding.root
        initUI()
        subscribeUI()
        return binding.root
    }

    private fun subscribeUI(){
        sharedViewModel.jumlahJamaah.observe(viewLifecycleOwner){jumlah ->
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
        addFragment()
        binding.stateCheckout.setStateDescriptionData(sharedViewModel.progressCheckoutDesc)
        requireActivity().onBackPressedDispatcher.addCallback(this@UmrohPaketPemesananFragment){
            sharedViewModel.searchPaket.observe(viewLifecycleOwner){
                val nav =
                    UmrohPaketPemesananFragmentDirections.actionFragmentUmrohPaketPemesananToFragmentUmrohPaket()
                binding.root.findNavController().navigate(nav)
            }
        }
        adapter = PemesananDataJamaahAdapter()
        binding.rvDataJamaah.adapter = adapter
    }

    private fun addFragment(){
        val fragmentManager = childFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.add(
            R.id.container_data_pemesan,
            UmrohPemesananDataFragment.newInstance()
        )
        transaction.commit()
    }
}