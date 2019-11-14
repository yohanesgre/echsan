package com.vira.echsan.ui.fragments.umroh

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
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.vira.echsan.adapters.umroh.ListPaketUmrohAdapter
import com.vira.echsan.databinding.FragmentUmrohHasilBinding
import com.vira.echsan.di.Injectable
import com.vira.echsan.di.injectViewModel
import com.vira.echsan.viewmodel.PaketUmrohSharedViewModel
import com.vira.echsan.viewmodel.UmrohViewModel
import javax.inject.Inject

class UmrohHasilFragment : Fragment(), Injectable{

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: UmrohViewModel
    private lateinit var sharedViewModel: PaketUmrohSharedViewModel
    private lateinit var binding: FragmentUmrohHasilBinding
    private val args: UmrohHasilFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = injectViewModel(viewModelFactory)
        sharedViewModel = ViewModelProviders.of(requireActivity()).get(PaketUmrohSharedViewModel::class.java)
        binding = FragmentUmrohHasilBinding.inflate(inflater, container, false).apply {
            clickFilter = View.OnClickListener {
                UmrohPaketFilterDialog.display(requireActivity().supportFragmentManager)
            }
        }
        initUI(binding)
        return binding.root
    }

    private fun initUI(binding:FragmentUmrohHasilBinding){
        binding.toolbar.title = "Paket Umroh"
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayShowHomeEnabled(true)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.subtitle = "${args.cariKota}, ${args.cariTanggal}"
        binding.rvUmrohPaket.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val adapter = ListPaketUmrohAdapter(sharedViewModel)
        subscribeUI(adapter)
        binding.rvUmrohPaket.adapter = adapter
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.rvUmrohPaket)
        requireActivity().onBackPressedDispatcher.addCallback(this@UmrohHasilFragment){
            val nav = UmrohHasilFragmentDirections.actionFragmentUmrohHasilToFragmentUmrohCari()
           binding.root.findNavController().navigate(nav)
        }
    }

    private fun subscribeUI(adapter: ListPaketUmrohAdapter){
        sharedViewModel.searchPaket.observe(viewLifecycleOwner){term ->
            /*viewModel.searchUmrohs(args.cariTipe, args.cariKota, args.cariTanggal).observe(viewLifecycleOwner){*/
            viewModel.searchUmrohs(term[0], term[1], term[2]).observe(viewLifecycleOwner){
            adapter.submitList(it)
            }
        }
    }
}