package com.vira.echsan.features.profile.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import com.vira.echsan.databinding.FragmentProfileDetilBinding
import com.vira.echsan.di.Injectable
import com.vira.echsan.di.injectViewModel
import com.vira.echsan.features.login.LoginActivity
import com.vira.echsan.features.profile.viewmodel.ProfileDetilViewModel
import javax.inject.Inject

class ProfileDetilFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: ProfileDetilViewModel
    private lateinit var binding: FragmentProfileDetilBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = injectViewModel(viewModelFactory)
        binding = FragmentProfileDetilBinding.inflate(inflater, container, false)
        initUI()
        subscribeUI()
        return binding.root
    }

    private fun initUI() {
        binding.toolbar.title = "Profile"
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayShowHomeEnabled(true)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        //binding.toolbar.subtitle = "${args.cariKota}, ${args.cariTanggal}"
        requireActivity().onBackPressedDispatcher.addCallback(this@ProfileDetilFragment) {
            requireActivity().finish()
        }
        binding.btnEdit.setOnClickListener {
            val nav =
                ProfileDetilFragmentDirections.actionProfileDetilFragmentToProfileEditFragment()
            binding.root.findNavController().navigate(nav)
        }
        binding.btnLogout.setOnClickListener {
            viewModel.logout()
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
            requireActivity().finishAffinity()
        }
    }

    private fun subscribeUI() {
        viewModel.getProfile.observe(viewLifecycleOwner) { profile ->
            if (profile != null) {
                binding.apply {
                    outputId.setText(profile.id.toString())
                    outputEmail.setText(profile.email)
                    outputNamaLengkap.setText(profile.name)
                    outputPhone.setText(profile.phone)
                    btnUbahPassword.setOnClickListener {
                        val nav =
                            ProfileDetilFragmentDirections.actionProfileDetilFragmentToProfileUbahPasswordFragment(
                                profile.default_password,
                                profile.id
                            )
                        this.root.findNavController().navigate(nav)
                    }
                }
            }
        }
    }
}