package com.vira.echsan.features.profile.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.vira.echsan.data.Result
import com.vira.echsan.databinding.FragmentProfileUbahPasswordBinding
import com.vira.echsan.di.Injectable
import com.vira.echsan.di.injectViewModel
import com.vira.echsan.features.profile.viewmodel.ProfileUbahPasswordViewModel
import javax.inject.Inject

class ProfileUbahPasswordFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: ProfileUbahPasswordViewModel
    private lateinit var binding: FragmentProfileUbahPasswordBinding
    private val args: ProfileUbahPasswordFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = injectViewModel(viewModelFactory)
        viewModel.userId = args.userId
        binding = FragmentProfileUbahPasswordBinding.inflate(inflater, container, false)
        initUI()
        subscribeUI()
        return binding.root
    }

    private fun initUI() {
        binding.toolbar.title = "Ubah Password"
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayShowHomeEnabled(true)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        //binding.toolbar.subtitle = "${args.cariKota}, ${args.cariTanggal}"
        requireActivity().onBackPressedDispatcher.addCallback(this@ProfileUbahPasswordFragment) {
            val nav =
                ProfileUbahPasswordFragmentDirections.actionProfileUbahPasswordFragmentToProfileDetilFragment()
            binding.root.findNavController().navigate(nav)
        }
        binding.btnSimpan.setOnClickListener {
            ubahPassword()
        }
        binding.outputPasswordLama.setText(args.oldPassword)
    }

    fun ubahPassword() {
        viewModel._ubahPasswordInfo.value = listOf(
            args.oldPassword,
            binding.inputPasswordBaru.text.toString(),
            binding.inputPasswordBaruUlangi.text.toString()
        )
    }

    private fun subscribeUI() {
        viewModel.getUbahPasswordResult.observe(this, Observer { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    if (result.data != null) {
                        val nav =
                            ProfileUbahPasswordFragmentDirections.actionProfileUbahPasswordFragmentToProfileDetilFragment()
                        binding.root.findNavController().navigate(nav)
                    }
                }
                Result.Status.LOADING -> {
                }
                Result.Status.ERROR -> {
                    Snackbar.make(binding.root, result.message!!, Snackbar.LENGTH_LONG).show()
                }
            }
        })
    }
}