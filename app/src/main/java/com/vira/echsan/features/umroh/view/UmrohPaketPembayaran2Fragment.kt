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
import com.vira.echsan.adapters.pembayaran.TipePembayaranParentAdapter
import com.vira.echsan.data.Result
import com.vira.echsan.data.entities.InputJamaahResp
import com.vira.echsan.databinding.FragmentUmrohCheckoutPembayaran2Binding
import com.vira.echsan.di.Injectable
import com.vira.echsan.di.injectViewModel
import com.vira.echsan.features.umroh.data.Payment
import com.vira.echsan.features.umroh.viewmodel.PaketPembayaran2ViewModel
import com.vira.echsan.features.umroh.viewmodel.UmrohSharedViewModel
import com.vira.echsan.utils.ConvertCurrencyToDouble
import com.vira.echsan.utils.ConvertToCurrency
import kotlinx.coroutines.runBlocking
import okhttp3.RequestBody
import javax.inject.Inject

class UmrohPaketPembayaran2Fragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: PaketPembayaran2ViewModel
    private lateinit var sharedViewModel: UmrohSharedViewModel
    private lateinit var binding: FragmentUmrohCheckoutPembayaran2Binding
    private val adapter by lazy { TipePembayaranParentAdapter() }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = injectViewModel(viewModelFactory)
        sharedViewModel =
            ViewModelProviders.of(requireActivity()).get(UmrohSharedViewModel::class.java)
        binding = FragmentUmrohCheckoutPembayaran2Binding.inflate(inflater, container, false)
        initUI()
        subscribeUI()
        return binding.root
    }

    private fun subscribeUI() {
        sharedViewModel.CurrentTransaction.observe(viewLifecycleOwner) {
            //binding.tvNomerTransaksi.text = it.transactionCode
            val payment = Payment(HashMap(), null)
            payment.text["user_id"] = RequestBody.create(
                okhttp3.MultipartBody.FORM, it.userId
            )
            payment.text["transaction_id"] = RequestBody.create(
                okhttp3.MultipartBody.FORM, it.transactionId
            )
            payment.text["paid"] = RequestBody.create(
                okhttp3.MultipartBody.FORM, sharedViewModel.totalPembayaran.toString()
            )
            sharedViewModel.UpdateCurrentPayment(payment)
        }
        sharedViewModel.SelectedPaket.observe(viewLifecycleOwner) { paket ->
            sharedViewModel.jumlahJamaah.observe(viewLifecycleOwner) { jumlah ->
                binding.tvPaketHarga.text = paket.price
                binding.tvPaketJamaah.text = "$jumlah Jamaah"
                binding.tvPaketHargaJamaah.text =
                    ConvertToCurrency(ConvertCurrencyToDouble(paket.price) * jumlah, null)
                binding.tvPaketHargaTotal.text =
                    ConvertToCurrency(ConvertCurrencyToDouble(paket.price) * jumlah, null)
                binding.tvPaketDpHarga.text =
                    ConvertToCurrency(sharedViewModel.totalPembayaran, null)
                binding.tvPaketDpHargaJamaah.text =
                    ConvertToCurrency(sharedViewModel.totalPembayaran * 2, null)
                binding.tvPaketDpTotalHarga.text =
                    ConvertToCurrency(sharedViewModel.totalPembayaran * 2, null)
                binding.tvPoint.text = paket.point.toString()
                viewModel.productId = paket.id
            }
        }
        viewModel.inputJamaah.observe(viewLifecycleOwner) {
            if (it.jamaahOrder!!.size != null) {
                viewModel.response.observe(viewLifecycleOwner) { result ->
                    when (result.status) {
                        Result.Status.SUCCESS -> {
                            println("Success")
                            if (result.data != null) {
                                runBlocking {
                                    sharedViewModel.UpdateCurrentTransaction(result.data)
                                }
                                bindView(result.data)
                            }
                        }
                        Result.Status.LOADING -> {
                        }
                        Result.Status.ERROR -> {
                            Snackbar.make(binding.root, result.message!!, Snackbar.LENGTH_LONG)
                                .show()
                        }
                    }
                }
            }
        }
    }

    private fun bindView(resp: InputJamaahResp) {
        resp.apply {
            val nav =
                UmrohPaketPembayaran2FragmentDirections.actionFragmentUmrohPaketPembayaran2ToFragmentUmrohPaketPembayaran3()
            binding.root.findNavController().navigate(nav)
        }
    }

    private fun initUI() {
        binding.toolbar.title = "PEMBAYARAN"
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayShowHomeEnabled(true)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding.stateCheckout.setStateDescriptionData(sharedViewModel.progressCheckoutDesc)
        requireActivity().onBackPressedDispatcher.addCallback(this@UmrohPaketPembayaran2Fragment) {
            val nav =
                UmrohPaketPembayaran2FragmentDirections.actionFragmentUmrohPaketPembayaran2ToFragmentUmrohPaketPembayaran()
            binding.root.findNavController().navigate(nav)
        }
        binding.setOnClick {
            viewModel.setInputJamaah(sharedViewModel.mInputJamaah)
        }
    }

}