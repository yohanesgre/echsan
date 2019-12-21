package com.vira.echsan.features.umroh.view

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
import com.google.android.material.snackbar.Snackbar
import com.vira.echsan.adapters.pemesanan.PemesananDataJamaahAdapter
import com.vira.echsan.databinding.FragmentUmrohCheckoutPemesananBinding
import com.vira.echsan.di.Injectable
import com.vira.echsan.di.injectViewModel
import com.vira.echsan.features.umroh.viewmodel.PaketPemesananViewModel
import com.vira.echsan.features.umroh.viewmodel.UmrohSharedViewModel
import javax.inject.Inject

class UmrohPaketPemesananFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: PaketPemesananViewModel
    private lateinit var sharedViewModel: UmrohSharedViewModel
    private lateinit var binding: FragmentUmrohCheckoutPemesananBinding
    private lateinit var adapter:PemesananDataJamaahAdapter

    private var paketId = 0
    private var amountJamaah = 0
    private var isReady = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = injectViewModel(viewModelFactory)
        sharedViewModel =
            ViewModelProviders.of(requireActivity()).get(UmrohSharedViewModel::class.java)
        binding = FragmentUmrohCheckoutPemesananBinding.inflate(inflater, container, false).apply{
            this.setOnClickTambah{
                if (sharedViewModel.jumlahJamaah.value!! < 5) {
                    sharedViewModel.addJumlahJamaah()
                }
            }

            this.setOnClickKurang {
                val inputJamaah = sharedViewModel.mInputJamaah
                if (amountJamaah > 0) {
                    if (inputJamaah.jamaahOrder!!.size != 0
                        && inputJamaah.jamaahOrder!!.getOrNull(amountJamaah - 1) != null
                    ) {
                        inputJamaah.jamaahOrder!!.removeAt(amountJamaah - 1)
                        inputJamaah.fullName!!.removeAt(amountJamaah - 1)
                        inputJamaah.gender!!.removeAt(amountJamaah - 1)
                        inputJamaah.birthPlace!!.removeAt(amountJamaah - 1)
                        inputJamaah.birthDate!!.removeAt(amountJamaah - 1)
                        inputJamaah.address!!.removeAt(amountJamaah - 1)
                        inputJamaah.RT!!.removeAt(amountJamaah - 1)
                        inputJamaah.RW!!.removeAt(amountJamaah - 1)
                        inputJamaah.kelurahan!!.removeAt(amountJamaah - 1)
                        inputJamaah.district!!.removeAt(amountJamaah - 1)
                        inputJamaah.city!!.removeAt(amountJamaah - 1)
                        inputJamaah.province!!.removeAt(amountJamaah - 1)
                        inputJamaah.posCode!!.removeAt(amountJamaah - 1)
                        inputJamaah.phone!!.removeAt(amountJamaah - 1)
                        sharedViewModel.minusJumlahJamaah()
                    } else {
                        sharedViewModel.minusJumlahJamaah()
                    }
                }
            }
        }
        context ?: return binding.root
        initUI()
        subscribeUI()
        return binding.root
    }

    private fun subscribeUI(){
        sharedViewModel.jumlahJamaah.observe(viewLifecycleOwner) { jumlah ->
            amountJamaah = jumlah
            sharedViewModel.setInputAmountToInputJamaah(jumlah)
            binding.jumlahJamaah = jumlah.toString()
            val listJamaah = mutableListOf<String>()
            (1..jumlah).forEach { i ->
                if (sharedViewModel.mInputJamaah.fullName!!.size == 0) {
                    listJamaah.add("Data Jamaah $i")
                } else if (i > sharedViewModel.mInputJamaah.fullName!!.size) {
                    listJamaah.add("Data Jamaah $i")
                } else {
                    listJamaah.add(sharedViewModel.mInputJamaah.fullName!![i - 1])
                }
            }
            adapter.itemList = listJamaah
            if (jumlah != 0)
                isReady = true
        }
        viewModel.dataPemesan.observe(viewLifecycleOwner) {
            sharedViewModel.setUserIDToInputJamaah(it.id)
            binding.tvPemesanNama.text = it.name
            binding.tvPemesanEmail.text = it.email
            binding.tvPemesanNohp.text = it.phone
        }
        sharedViewModel.SelectedPaket.observe(viewLifecycleOwner) {
            paketId = it.id
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
                UmrohPaketPemesananFragmentDirections.actionFragmentUmrohPaketPemesananToFragmentUmrohPaket(
                    paketId
                )
            binding.root.findNavController().navigate(nav)
        }
        adapter = PemesananDataJamaahAdapter()
        binding.rvDataJamaah.adapter = adapter
        binding.cbDataSesuai.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                if (isReady) {
                    binding.setOnClickLanjut {
                        val nav =
                            UmrohPaketPemesananFragmentDirections.actionFragmentUmrohPaketPemesananToFragmentUmrohPaketPembayaran()
                        binding.root.findNavController().navigate(nav)
                    }
                } else {
                    Snackbar.make(
                        binding.root,
                        "Silakan masukan data jamaah terlebih dahulu!",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}