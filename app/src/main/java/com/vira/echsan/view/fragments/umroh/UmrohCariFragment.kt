package com.vira.echsan.ui.fragments.umroh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.vira.echsan.databinding.FragmentUmrohCariBinding
import com.vira.echsan.di.Injectable
import com.vira.echsan.di.injectViewModel
import com.vira.echsan.utils.ConnectivityUtil
import com.vira.echsan.viewmodel.PaketUmrohsViewModel
import com.vira.echsan.viewmodel.UmrohSharedViewModel
import javax.inject.Inject

class UmrohCariFragment : Fragment(), Injectable{

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: PaketUmrohsViewModel
    private lateinit var sharedViewModel: UmrohSharedViewModel

    private lateinit var binding: FragmentUmrohCariBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = injectViewModel(viewModelFactory)
        viewModel.connectivityAvailable = ConnectivityUtil.isConnected(requireActivity())
        sharedViewModel =
            ViewModelProviders.of(requireActivity()).get(UmrohSharedViewModel::class.java)
        binding = FragmentUmrohCariBinding.inflate(inflater, container, false)
        initUI()
        return binding.root
    }

    private fun subscribeUI() {
        /*viewModel.paketUmrohs.observe(viewLifecycleOwner){
            for (umroh in it)
            binding.etUmrohCariTipe.setAdapter(umroh.product_category.)
        }*/
    }

    private fun initUI(){
        binding.toolbar.title = "CARI PAKET UMROH"
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayShowHomeEnabled(true)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding.setOnClick {
            /*
                        sharedViewModel.searchPaket(binding.etUmrohCariTipe.text.toString(),
                            binding.etUmrohCariKota.text.toString(),
                            binding.etUmrohCariTglBerangkat.text.toString())*/
            val nav = UmrohCariFragmentDirections.actionFragmentUmrohCariToFragmentUmrohHasil(
                /*binding.etUmrohCariTipe.text.toString(),
                binding.etUmrohCariKota.text.toString(),
                binding.etUmrohCariTglBerangkat.text.toString()*/
            )
            binding.root.findNavController().navigate(nav)
        }
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            binding.etUmrohCariTipe.requestFocus()
            binding.etUmrohCariTipe.showSoftInputOnFocus = false
            binding.etUmrohCariKota.requestFocus()
            binding.etUmrohCariKota.showSoftInputOnFocus = false
            binding.etUmrohCariTglBerangkat.requestFocus()
            binding.etUmrohCariTglBerangkat.showSoftInputOnFocus = false
            binding.etUmrohCariHarga.requestFocus()
            binding.etUmrohCariHarga.showSoftInputOnFocus = false
        }*/
    }
}