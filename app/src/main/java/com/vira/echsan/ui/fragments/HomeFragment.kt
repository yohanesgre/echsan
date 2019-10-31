package com.vira.echsan.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.vira.echsan.databinding.FragmentHomeBinding
import com.vira.echsan.di.Injectable
import com.vira.echsan.di.injectViewModel
import com.vira.echsan.ui.activities.umroh_haji.UmrohActivity
import com.vira.echsan.adapters.CarouselAdapter
import com.vira.echsan.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject


class HomeFragment : Fragment(), Injectable{

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: HomeViewModel

    private lateinit var binding: FragmentHomeBinding
    private val adapter: CarouselAdapter by lazy { CarouselAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        viewModel = injectViewModel(viewModelFactory)

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        context?: return binding.root
        initUI(binding)
        subscribeUI(adapter)
        return binding.root
    }

    private fun initUI(binding:FragmentHomeBinding){
        binding.btnHomeUmrohHaji.setOnClickListener {
            val intent = Intent(activity, UmrohActivity::class.java)
            startActivity(intent)
        }
        binding.rvCarousel.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        binding.rvCarousel.adapter = adapter
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.rvCarousel)
    }

    private fun subscribeUI(adapter: CarouselAdapter){
        viewModel.promoSets.observe(viewLifecycleOwner){ promos ->
            adapter.submitList(promos)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_home_umroh_haji.setOnClickListener {
            val intent = Intent(activity, UmrohActivity::class.java)
            activity!!.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }
    companion object {
        fun newInstance() : HomeFragment = HomeFragment()
    }
}