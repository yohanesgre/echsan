package com.vira.echsan.ui.fragments.umroh

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.vira.echsan.R
import com.vira.echsan.databinding.FragmentHomeBinding
import com.vira.echsan.databinding.FragmentUmrohCariBinding
import com.vira.echsan.di.Injectable
import com.vira.echsan.di.injectViewModel
import com.vira.echsan.viewmodel.HomeViewModel
import com.vira.echsan.viewmodel.PaketUmrohSharedViewModel
import com.vira.echsan.viewmodel.UmrohViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class UmrohCariFragment : Fragment(), Injectable{

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: UmrohViewModel
    private lateinit var sharedViewModel: PaketUmrohSharedViewModel

    private lateinit var binding: FragmentUmrohCariBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = injectViewModel(viewModelFactory)
        sharedViewModel = ViewModelProviders.of(requireActivity()).get(PaketUmrohSharedViewModel::class.java)
        binding = FragmentUmrohCariBinding.inflate(inflater, container, false)
        initUI()
        return binding.root
    }

    private fun initUI(){
        binding.toolbarTitle.text = "Umroh"
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayShowHomeEnabled(true)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding.setOnClick {
            sharedViewModel.searchPaket(binding.etUmrohCariTipe.text.toString(),
                binding.etUmrohCariKota.text.toString(),
                binding.etUmrohCariTglBerangkat.text.toString())
            val nav = UmrohCariFragmentDirections.actionFragmentUmrohCariToFragmentUmrohHasil(
                binding.etUmrohCariTipe.text.toString(),
                binding.etUmrohCariKota.text.toString(),
                binding.etUmrohCariTglBerangkat.text.toString())
            binding.root.findNavController().navigate(nav)
        }
        binding.etUmrohCariTipe.setAdapter(ArrayAdapter<String>(requireContext(), R.layout.material_spinner_item, viewModel.tipeUmroh))
        binding.etUmrohCariKota.setAdapter(ArrayAdapter<String>(requireContext(), R.layout.material_spinner_item, viewModel.kotaUmroh))
        binding.etUmrohCariTglBerangkat.setAdapter(ArrayAdapter<String>(requireContext(), R.layout.material_spinner_item, viewModel.tglUmroh))
        binding.etUmrohCariJamaah.setAdapter(ArrayAdapter<Int>(requireContext(), R.layout.material_spinner_item, viewModel.jamaahUmroh))
    }
}