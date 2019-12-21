package com.vira.echsan.features.umroh.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.github.dhaval2404.imagepicker.ImagePicker
import com.github.dhaval2404.imagepicker.constant.ImageProvider
import com.google.android.material.snackbar.Snackbar
import com.vira.echsan.data.Result
import com.vira.echsan.data.entities.PaymentResp
import com.vira.echsan.databinding.FragmentUmrohCheckoutPembayaran3Binding
import com.vira.echsan.di.Injectable
import com.vira.echsan.di.injectViewModel
import com.vira.echsan.features.umroh.viewmodel.PaketPembayaran3ViewModel
import com.vira.echsan.features.umroh.viewmodel.UmrohSharedViewModel
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject


class UmrohPaketPembayaran3Fragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: PaketPembayaran3ViewModel
    private lateinit var sharedViewModel: UmrohSharedViewModel
    private lateinit var binding: FragmentUmrohCheckoutPembayaran3Binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = injectViewModel(viewModelFactory)
        sharedViewModel =
            ViewModelProviders.of(requireActivity()).get(UmrohSharedViewModel::class.java)
        binding = FragmentUmrohCheckoutPembayaran3Binding.inflate(inflater, container, false)
        initUI()
        subscribeUI()
        return binding.root
    }

    private fun subscribeUI() {
        sharedViewModel.CurrentTransaction.observe(viewLifecycleOwner) {
            binding.tvNomerTransaksi.text = it.transactionCode
        }
        viewModel.imageUploadFile.observe(viewLifecycleOwner) { imageFile ->
            sharedViewModel.CurrentPayment.observe(viewLifecycleOwner) { payment ->
                val reqFile: RequestBody = RequestBody.create(MediaType.parse("image/*"), imageFile)
                val body = MultipartBody.Part.createFormData("paid_file", imageFile.name, reqFile)
                payment.paidFile = body
                viewModel.setInputPayment(payment)
                viewModel.inputPayment.observe(viewLifecycleOwner) {
                    viewModel.inputPaymentResp.observe(viewLifecycleOwner) { result ->
                        when (result.status) {
                            Result.Status.SUCCESS -> {
                                println("Success")
                                if (result.data != null) {
                                    bindView(result.data)
                                }
                            }
                            Result.Status.LOADING -> {
                            }
                            Result.Status.ERROR -> {
                                Snackbar.make(binding.root, result.message!!, Snackbar.LENGTH_LONG)
                                    .show()
                                println("Error Post Payment: ${result.message!!}")
                            }
                        }
                    }
                }
            }
        }
    }

    private fun bindView(resp: PaymentResp) {
        resp.apply {
            sharedViewModel.UpdateFinalPayment(resp)
            val nav =
                UmrohPaketPembayaran3FragmentDirections.actionFragmentUmrohPaketPembayaran3ToFragmentUmrohPaketBerhasil()
            binding.root.findNavController().navigate(nav)
        }
    }

    private fun initUI() {
        binding.toolbar.title = "Pembayaran"
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayShowHomeEnabled(true)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding.stateCheckout.setStateDescriptionData(sharedViewModel.progressCheckoutDesc)
        requireActivity().onBackPressedDispatcher.addCallback(this@UmrohPaketPembayaran3Fragment) {
            requireActivity().finish()
        }

        binding.setOnClickSudahBayar {
            ImagePicker.with(this)
                .provider(ImageProvider.CAMERA) // Default will be ImageProvider.BOTH
                .compress(2048) // Final image size will be less than 1 MB(Optional)
                .start(CAMERA_IMAGE_REQ_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            // File object will not be null for RESULT_OK
            val file = ImagePicker.getFile(data)

            Log.e("TAG", "Path:${file?.absolutePath}")
            when (requestCode) {
                CAMERA_IMAGE_REQ_CODE -> {
                    viewModel.UpdateImageUploadFile(file!!)
                }
            }
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Snackbar.make(binding.root, ImagePicker.getError(data), Snackbar.LENGTH_SHORT).show()
        } else {
            Snackbar.make(binding.root, "Task Cancelled", Snackbar.LENGTH_SHORT).show()
        }
    }

    companion object {

        private const val PROFILE_IMAGE_REQ_CODE = 101
        private const val GALLERY_IMAGE_REQ_CODE = 102
        private const val CAMERA_IMAGE_REQ_CODE = 103
        private const val DEFAULT_IMAGE_URL =
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTE2nnhjrSnA-nOn-pmBR1w1yIO5VytHaju-l-rUjNixn-w8oE4"
    }
}