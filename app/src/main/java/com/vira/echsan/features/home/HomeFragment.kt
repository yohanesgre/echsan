package com.vira.echsan.features.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.*
import com.vira.echsan.R
import com.vira.echsan.databinding.FragmentHomeBinding
import com.vira.echsan.di.Injectable
import com.vira.echsan.di.injectViewModel
import com.vira.echsan.features.home.adapter.KategoriUmrohHomeAdapter
import com.vira.echsan.features.home.adapter.RekomendasiHomeAdapter
import com.vira.echsan.features.home.adapter.TopCarouselAdapter
import com.vira.echsan.features.home.viewmodel.HomeViewModel
import com.vira.echsan.features.social.SocialActivity
import com.vira.echsan.features.umroh.UmrohActivity
import com.vira.echsan.utils.DpToPx
import kotlinx.coroutines.Runnable
import javax.inject.Inject
import kotlin.math.roundToInt


class HomeFragment : Fragment(), Injectable{
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: HomeViewModel

    private lateinit var binding: FragmentHomeBinding
    private lateinit var topCarouselAdapter: TopCarouselAdapter
    private lateinit var rekomendasiHomeAdapter: RekomendasiHomeAdapter
    private lateinit var kategoriUmrohHomeAdapter: KategoriUmrohHomeAdapter

    private lateinit var autoScrollTopCarousel: Runnable
    private lateinit var handler: Handler

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        viewModel = injectViewModel(viewModelFactory)
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        initUI()
        subscribeUI()
        return binding.root
    }

    private fun initUI() {
        topCarouselAdapter = TopCarouselAdapter()
        rekomendasiHomeAdapter = RekomendasiHomeAdapter()
        kategoriUmrohHomeAdapter = KategoriUmrohHomeAdapter()
        val listItem = listOf(
            R.drawable.carousel_1, R.drawable.carousel_2, R.drawable.carousel_3
        )
        val listItemString = listOf(
            "Paket umroh dengan beragam pilihan sesuai kebutuhan Anda",
            "Nikmati perjalanan umroh dengan fasilitas yang terbaik"
        )
        kategoriUmrohHomeAdapter.setItemList(listItemString)
        rekomendasiHomeAdapter.setItemList(listItem)
        topCarouselAdapter.setItemList(listItem)
        val linearLayoutManager = object : LinearLayoutManager(requireContext()) {
            override fun smoothScrollToPosition(
                recyclerView: RecyclerView?,
                state: RecyclerView.State?,
                position: Int
            ) {
                try {
                    val smoothScroller: LinearSmoothScroller =
                        object : LinearSmoothScroller(requireContext()) {
                            private val SPEED =
                                150f // Change this value (default=25f)

                            override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
                                return SPEED / displayMetrics.densityDpi
                            }
                        }
                    smoothScroller.targetPosition = position
                    startSmoothScroll(smoothScroller)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        binding.layoutUmroh.setOnClickListener {
            val intent = Intent(activity, UmrohActivity::class.java)
            startActivity(intent)
        }

        binding.layoutSosial.setOnClickListener {
            val intent = Intent(activity, SocialActivity::class.java)
            startActivity(intent)
        }

        binding.layoutTravel.setOnClickListener {
            showAlertDevelopment()
        }

        binding.layoutBenefit.setOnClickListener {
            showAlertDevelopment()
        }

        binding.layoutDoa.setOnClickListener {
            showAlertDevelopment()
        }

        binding.layoutShop.setOnClickListener {
            showAlertDevelopment()
        }

        binding.layoutMedsos.setOnClickListener {
            showAlertDevelopment()
        }

        binding.layoutMember.setOnClickListener {
            showAlertDevelopment()
        }

        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.rvTopCarousel.apply {
            adapter = topCarouselAdapter
            layoutManager = linearLayoutManager
        }
        binding.rvRekomendasiHome.apply {
            adapter = rekomendasiHomeAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
        binding.rvKatUmrohHome.apply {
            adapter = kategoriUmrohHomeAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
        val pagerSnapperTopCarousel = PagerSnapHelper()
        val snapperRekomendasi = LinearSnapHelper()
        val snapperkategoriUmrohHome = LinearSnapHelper()
        pagerSnapperTopCarousel.attachToRecyclerView(binding.rvTopCarousel)
        snapperRekomendasi.attachToRecyclerView(binding.rvRekomendasiHome)
        snapperkategoriUmrohHome.attachToRecyclerView(binding.rvKatUmrohHome)
        binding.indicator.attachToRecyclerView(binding.rvTopCarousel, pagerSnapperTopCarousel)
        binding.rvTopCarousel.setPadding(
            DpToPx(requireContext(), 10),
            0,
            DpToPx(requireContext(), 10),
            0
        )
    }

    private fun autoScroll() {
        val speedScroll = 5000L
        handler = Handler()
        autoScrollTopCarousel = object : Runnable {
            var count = 0
            override fun run() {
                if (count == topCarouselAdapter.itemCount) count = -1
                if (count < topCarouselAdapter.itemCount) {
                    binding.rvTopCarousel.smoothScrollToPosition(++count)
                    handler.postDelayed(this, speedScroll)
                }
            }
        }
        handler.postDelayed(autoScrollTopCarousel, speedScroll)
    }

    override fun onResume() {
        super.onResume()
        autoScroll()
    }

    override fun onPause() {
        handler.removeCallbacks(autoScrollTopCarousel)
        super.onPause()
    }

    private fun subscribeUI(){
        viewModel.promoSets.observe(viewLifecycleOwner){ promos ->
            binding.indicator.createIndicators(promos.size, (promos.size / 2f).roundToInt())
        }
    }

    private fun showAlertDevelopment() {
        val alertDialog = AlertDialog.Builder(requireActivity())
        alertDialog.setTitle("Sedang dalam Pengembangan")
            .setMessage("Silakan gunakan fitur lain yang ada...")
            .setCancelable(true)
            .setPositiveButton("OK") { dialog, id ->
                dialog.dismiss()
            }
            .create()
            .show()

    }
}