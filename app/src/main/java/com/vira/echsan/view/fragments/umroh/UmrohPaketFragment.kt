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
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.vira.echsan.R
import com.vira.echsan.data.Result
import com.vira.echsan.databinding.FragmentUmrohPaketBinding
import com.vira.echsan.di.Injectable
import com.vira.echsan.di.injectViewModel
import com.vira.echsan.ui.fragments.umroh.paket.UmrohPaketDetilFragment
import com.vira.echsan.view.fragments.umroh.paket.UmrohPaketFasilitasFragment
import com.vira.echsan.view.fragments.umroh.paket.UmrohPaketHotelFragment
import com.vira.echsan.view.fragments.umroh.paket.UmrohPaketPenerbanganFragment
import com.vira.echsan.view.fragments.umroh.paket.UmrohPaketRencanaPerjalananFragment
import com.vira.echsan.viewmodel.PaketUmrohViewModel
import com.vira.echsan.viewmodel.UmrohSharedViewModel
import javax.inject.Inject

class UmrohPaketFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: PaketUmrohViewModel
    private lateinit var sharedViewModel: UmrohSharedViewModel
    private lateinit var binding: FragmentUmrohPaketBinding
    private val args: UmrohPaketFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = injectViewModel(viewModelFactory)
        sharedViewModel =
            ViewModelProviders.of(requireActivity()).get(UmrohSharedViewModel::class.java)
        viewModel.id = args.paketId
        binding = FragmentUmrohPaketBinding.inflate(inflater, container, false)
        context ?: return binding.root
        initUI()
        subscribeUI()
        return binding.root
    }

    private fun subscribeUI(){
        viewModel.paketUmrohResult.observe(viewLifecycleOwner) { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    addFragment()
                    UmrohPaketDetilFragment.newInstance().binding.apply {
                        tvUmrohPaketDetilTravel.text = result.data!!.travel_vendor.trav_name
                        tvUmrohPaketDetilBulanBerangkat.text = result.data.date_of_departure
                        tvUmrohPaketDetilDurasi.text = result.data.day_amount.toString()
                        tvUmrohPaketDetilLokasi.text = result.data.departure_city.city_name
                        tvUmrohPaketDetilKuota.text = result.data.quota.toString()
                        tvUmrohPaketDetilPoint.text = result.data.point.toString()
                    }
                }
                Result.Status.LOADING -> {
                }
                Result.Status.ERROR -> {
                    Snackbar.make(binding.root, result.message!!, Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun initUI(){
        var isToolbarShown = true
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity).supportActionBar!!.title = ""
        (activity as AppCompatActivity).supportActionBar!!.setDisplayShowHomeEnabled(true)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding.buttonBayar.setOnClickListener {
            val nav = UmrohPaketFragmentDirections.actionFragmentUmrohPaketToFragmentUmrohPemesanan()
            binding.root.findNavController().navigate(nav)
        }
        requireActivity().onBackPressedDispatcher.addCallback(this@UmrohPaketFragment){
            sharedViewModel.searchPaket.observe(viewLifecycleOwner){
                val nav =
                    UmrohPaketFragmentDirections.actionFragmentUmrohPaketToFragmentUmrohHasil()
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