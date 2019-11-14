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
import com.vira.echsan.adapters.pembayaran.TipePembayaranParentAdapter
import com.vira.echsan.databinding.FragmentUmrohCheckoutPembayaranBinding
import com.vira.echsan.view.fragments.umroh.pembayaran.UmrohPembayaranPaketFragment
import com.vira.echsan.view.fragments.umroh.pemesanan.UmrohPemesananDataFragment
import com.vira.echsan.viewmodel.PaketUmrohSharedViewModel
import javax.inject.Inject

class UmrohPaketPembayaranFragment : Fragment(){
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var sharedViewModel: PaketUmrohSharedViewModel
    private lateinit var binding: FragmentUmrohCheckoutPembayaranBinding
    private val adapter by lazy { TipePembayaranParentAdapter() }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedViewModel =
            ViewModelProviders.of(requireActivity()).get(PaketUmrohSharedViewModel::class.java)
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

    private fun subscribeUI(){

    }

    private fun initUI(){
        binding.toolbar.title = "PEMBAYARAN"
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayShowHomeEnabled(true)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        addFragment()
        binding.stateCheckout.setStateDescriptionData(sharedViewModel.progressCheckoutDesc)
        requireActivity().onBackPressedDispatcher.addCallback(this@UmrohPaketPembayaranFragment){
            sharedViewModel.searchPaket.observe(viewLifecycleOwner){
                val nav =
                    UmrohPaketPembayaranFragmentDirections.actionFragmentUmrohPaketPembayaranToFragmentUmrohPaketPemesanan()
                binding.root.findNavController().navigate(nav)
            }
        }
        binding.rvTipePembayaran.adapter = adapter
        adapter.addSectionItem(sharedViewModel.tipePembayaranSection)
    }

    private fun addFragment(){
        val fragmentManager = childFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(
            R.id.container_paket_umroh,
            UmrohPembayaranPaketFragment.newInstance()
        )
        transaction.commit()
    }
}