package com.vira.echsan.view.fragments

import alirezat775.lib.carouselview.Carousel
import alirezat775.lib.carouselview.CarouselListener
import alirezat775.lib.carouselview.CarouselView
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.vira.echsan.adapters.PromoAdapter
import com.vira.echsan.data.CarouselModel
import com.vira.echsan.databinding.FragmentHomeBinding
import com.vira.echsan.di.Injectable
import com.vira.echsan.di.injectViewModel
import com.vira.echsan.ui.activities.umroh_haji.UmrohActivity
import com.vira.echsan.viewmodel.HomeViewModel
import javax.inject.Inject
import kotlin.math.roundToInt


class HomeFragment : Fragment(), Injectable{
    val TAG: String = this::class.java.name
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: HomeViewModel

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: PromoAdapter
    private lateinit var carousel: Carousel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        viewModel = injectViewModel(viewModelFactory)
        binding = FragmentHomeBinding.inflate(inflater, container, false).apply {
            setOnClickUmroh {
                val intent = Intent(activity, UmrohActivity::class.java)
                startActivity(intent)
            }
        }
        initUI(binding)
        subscribeUI()
        return binding.root
    }

    private fun initUI(binding:FragmentHomeBinding){
        val pagerSnapper = PagerSnapHelper()
        adapter = PromoAdapter()
        carousel = Carousel(requireContext(), binding.carouselPromo, adapter)
        carousel.setOrientation(CarouselView.HORIZONTAL, false)
        carousel.autoScroll(true, 3000, true)
        carousel.scaleView(true)
        pagerSnapper.attachToRecyclerView(carousel.getView() as RecyclerView)
        carousel.addCarouselListener(object : CarouselListener {
            override fun onPositionChange(position: Int) {
                Log.d(TAG, "currentPosition : $position")
                binding.indicator.animatePageSelected(position)
            }

            override fun onScroll(dx: Int, dy: Int) {
                Log.d(TAG, "onScroll dx : $dx -- dy : $dx")
            }
        })
    }

    private fun subscribeUI(){
        viewModel.promoSets.observe(viewLifecycleOwner){ promos ->
            adapter.setList(promos)
            promos.forEach{
                val item = CarouselModel(it)
                carousel.add(item)
            }
            binding.indicator.createIndicators(promos.size, carousel.getCurrentPosition())
        }
    }

    companion object {
        fun newInstance() : HomeFragment = HomeFragment()
    }
}