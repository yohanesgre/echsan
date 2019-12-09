package com.vira.echsan.ui.fragments.umroh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.vira.echsan.adapters.umroh.PaketHotelAdapter
import com.vira.echsan.adapters.umroh.PaketPenerbanganAdapter
import com.vira.echsan.adapters.umroh.PaketRencanaPerjalananAdapter
import com.vira.echsan.data.Result
import com.vira.echsan.data.entities.PaketUmroh
import com.vira.echsan.databinding.FragmentUmrohPaketBinding
import com.vira.echsan.di.Injectable
import com.vira.echsan.di.injectViewModel
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

    /*Adapter RecyclerView*/
    private lateinit var hotelAdapter: PaketHotelAdapter
    private lateinit var penerbanganAdapter: PaketPenerbanganAdapter
    private lateinit var rencanaPerjalananAdapter: PaketRencanaPerjalananAdapter
    /*----------------------------------*/

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
        //context ?: return binding.root
        initUI()
        subscribeUI()
        return binding.root
    }

    private fun subscribeUI(){
        viewModel.paketUmrohResult.observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    println("Success")
                    if (result.data != null) {
                        bindView(result.data)
                        sharedViewModel.UpdateSelectedPaket(result.data)
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

    private fun bindView(paketUmroh: PaketUmroh) {
        paketUmroh.apply {
            binding.tvUmrohDetilPaket.text = this.name
            binding.tvUmrohPaketDetilTravel.text = this.travel_vendor.trav_name
            binding.tvUmrohPaketDetilBulanBerangkat.text = this.date_of_departure
            binding.tvUmrohPaketDetilDurasi.text = this.day_amount.toString()
            binding.tvUmrohPaketDetilLokasi.text = this.departure_city.city_name
            binding.tvUmrohPaketDetilKuota.text = this.quota.toString()
            binding.tvUmrohPaketDetilPoint.text = this.point.toString()
            val listHotel =
                listOf(listOf("Makkah", this.makkah_hotel), listOf("Madinah", this.madinah_hotel))
            val listPenerbangan = listOf(
                listOf(
                    "Keberangkatan",
                    this.departure_city.city_name,
                    "Jeddah",
                    this.departure_plane.dep_plane_name
                ),
                listOf(
                    "Kepulangan",
                    "Jeddah",
                    this.departure_city.city_name,
                    this.return_plane.ret_plane_name
                )
            )
            hotelAdapter.submitList(listHotel)
            penerbanganAdapter.submitList(listPenerbangan)
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
            val nav =
                UmrohPaketFragmentDirections.actionFragmentUmrohPaketToFragmentUmrohHasil()
            binding.root.findNavController().navigate(nav)
        }
        hotelAdapter = PaketHotelAdapter()
        penerbanganAdapter = PaketPenerbanganAdapter()
        rencanaPerjalananAdapter = PaketRencanaPerjalananAdapter()

        binding.rvUmrohPaketHotel.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvUmrohPaketPenerbangan.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvUmrohPaketHotel.adapter = hotelAdapter
        binding.rvUmrohPaketPenerbangan.adapter = penerbanganAdapter
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

    }

    /* private fun addFragment(){
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
     }*/
}