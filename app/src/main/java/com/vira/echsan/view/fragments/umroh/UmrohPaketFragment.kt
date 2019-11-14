package com.vira.echsan.ui.fragments.umroh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import com.vira.echsan.R
import com.vira.echsan.databinding.FragmentUmrohPaketBinding
import com.vira.echsan.di.Injectable
import com.vira.echsan.di.injectViewModel
import com.vira.echsan.ui.fragments.umroh.paket.UmrohPaketDetilFragment
import com.vira.echsan.view.fragments.umroh.paket.UmrohPaketFasilitasFragment
import com.vira.echsan.utils.ConvertToCurrency
import com.vira.echsan.view.fragments.umroh.paket.UmrohPaketHotelFragment
import com.vira.echsan.view.fragments.umroh.paket.UmrohPaketPenerbanganFragment
import com.vira.echsan.view.fragments.umroh.paket.UmrohPaketRencanaPerjalananFragment
import com.vira.echsan.viewmodel.PaketUmrohSharedViewModel
import com.vira.echsan.viewmodel.UmrohViewModel
import javax.inject.Inject

class UmrohPaketFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: UmrohViewModel
    private lateinit var sharedViewModel: PaketUmrohSharedViewModel
    private lateinit var binding: FragmentUmrohPaketBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = injectViewModel(viewModelFactory)
        sharedViewModel =
            ViewModelProviders.of(requireActivity()).get(PaketUmrohSharedViewModel::class.java)
        binding = FragmentUmrohPaketBinding.inflate(inflater, container, false)
        context ?: return binding.root
        initUI()
        subscribeUI()
        return binding.root
    }

    private fun subscribeUI(){
        sharedViewModel.selectedPaket.observe(viewLifecycleOwner){
            sharedViewModel.setHargaTotal(it.harga)
        }
        sharedViewModel.hargaTotal.observe(viewLifecycleOwner){
            binding.tvHarga.text = "Harga mulai dari " + ConvertToCurrency(null, it)
        }
    }

    private fun initUI(){
        var isToolbarShown = true
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity).supportActionBar!!.title = ""
        (activity as AppCompatActivity).supportActionBar!!.setDisplayShowHomeEnabled(true)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        addFragment()
        binding.buttonBayar.setOnClickListener {
            val nav = UmrohPaketFragmentDirections.actionFragmentUmrohPaketToUmrohPaketPemesananFragment()
            binding.root.findNavController().navigate(nav)
        }
        requireActivity().onBackPressedDispatcher.addCallback(this@UmrohPaketFragment){
            sharedViewModel.searchPaket.observe(viewLifecycleOwner){
                val nav =
                    UmrohPaketFragmentDirections.actionFragmentUmrohPaketToFragmentUmrohHasil(
                        it[0],
                        it[1],
                        it[2]
                    )
                binding.root.findNavController().navigate(nav)
            }
        }
        binding.nestedScrollview.setOnScrollChangeListener(
            NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, _ ->

                // User scrolled past image to height of toolbar and the title text is
                // underneath the toolbar, so the toolbar should be shown.
                val shouldHideToolbar = scrollY > binding.toolbar.height

                // The new state of the toolbar differs from the previous state; update
                // appbar and toolbar attributes.
                if (isToolbarShown == shouldHideToolbar) {
                    isToolbarShown = shouldHideToolbar
                    binding.appBarLayout.isActivated = false
                }else{
                    isToolbarShown = shouldHideToolbar
                    binding.appBarLayout.isActivated = true
                }
            }
        )
        binding.toolbar.setNavigationOnClickListener {
            sharedViewModel.searchPaket.observe(viewLifecycleOwner){
                val nav =
                    UmrohPaketFragmentDirections.actionFragmentUmrohPaketToFragmentUmrohHasil(
                        it[0],
                        it[1],
                        it[2]
                    )
                binding.root.findNavController().navigate(nav)
            }
        }

        /*binding.toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_share -> {
                    createShareIntent()
                    true
                }
                else -> false
            }
        }*/
    }

    private fun addFragment(){
        val fragmentManager = childFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.add(R.id.container_fragment_detil,
            UmrohPaketDetilFragment.newInstance()
        )
        transaction.add(R.id.container_fragment_fasilitas,
            UmrohPaketFasilitasFragment.newInstance()
        )
        transaction.add(R.id.container_fragment_penerbangan,
            UmrohPaketPenerbanganFragment.newInstance()
        )
        transaction.add(R.id.container_fragment_hotel,
            UmrohPaketHotelFragment.newInstance()
        )
        transaction.add(R.id.container_fragment_rencana_perjalanan,
            UmrohPaketRencanaPerjalananFragment.newInstance()
        )
        transaction.commit()
    }
}