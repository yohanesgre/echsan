package com.vira.echsan.features.social.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.vira.echsan.adapters.umroh.ListPaketUmrohAdapter
import com.vira.echsan.databinding.FragmentSocialDonateBinding
import com.vira.echsan.di.Injectable

class SocialDonateFragment : Fragment(), Injectable {

    /*@Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: PaketUmrohsViewModel*/
    private lateinit var binding: FragmentSocialDonateBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //viewModel = injectViewModel(viewModelFactory)
        //viewModel.connectivityAvailable = ConnectivityUtil.isConnected(context!!)
        binding = FragmentSocialDonateBinding.inflate(inflater, container, false)
        initUI()
        return binding.root
    }

    private fun initUI() {
        binding.toolbar.title = "Sosial"
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayShowHomeEnabled(true)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        requireActivity().onBackPressedDispatcher.addCallback(this@SocialDonateFragment) {
            val nav = SocialDonateFragmentDirections.actionSocialDonateFragmentToSocialFragment()
            binding.root.findNavController().navigate(nav)
        }
    }

    private fun subscribeUI(adapter: ListPaketUmrohAdapter) {

    }
}