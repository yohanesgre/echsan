package com.vira.echsan.features.social.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.vira.echsan.adapters.umroh.ListPaketUmrohAdapter
import com.vira.echsan.databinding.FragmentSocialBinding
import com.vira.echsan.di.Injectable

class SocialFragment : Fragment(), Injectable {

    /*@Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: PaketUmrohsViewModel*/
    private lateinit var binding: FragmentSocialBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //viewModel = injectViewModel(viewModelFactory)
        //viewModel.connectivityAvailable = ConnectivityUtil.isConnected(context!!)
        binding = FragmentSocialBinding.inflate(inflater, container, false)
        initUI()
        return binding.root
    }

    private fun initUI() {
        binding.toolbar.title = "Sosial"
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayShowHomeEnabled(true)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        //binding.toolbar.subtitle = "${args.cariKota}, ${args.cariTanggal}"
        requireActivity().onBackPressedDispatcher.addCallback(this@SocialFragment) {
            requireActivity().finish()
        }
    }

    private fun subscribeUI(adapter: ListPaketUmrohAdapter) {

    }
}