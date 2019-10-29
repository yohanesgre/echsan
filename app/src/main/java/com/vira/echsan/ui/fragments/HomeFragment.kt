package com.vira.echsan.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.vira.echsan.databinding.FragmentHomeBinding
import com.vira.echsan.ui.activities.umroh_haji.UmrohActivity
import com.vira.echsan.ui.adapters.CarouselAdapter
import com.vira.echsan.ui.viewmodel.HomeViewModel
import com.vira.echsan.utils.InjectorUtils
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(){

    private val viewModel: HomeViewModel by viewModels{
        InjectorUtils.provideHomeViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        context?: return binding.root
        initUI(binding)
        return binding.root
    }

    private fun initUI(binding:FragmentHomeBinding){
        binding.btnHomeUmrohHaji.setOnClickListener {
            val intent = Intent(activity, UmrohActivity::class.java)
            startActivity(intent)
        }
        binding.rvCarousel.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val adapter = CarouselAdapter()
        subscribeUI(adapter)
        binding.rvCarousel.adapter = adapter
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.rvCarousel)
    }

    private fun subscribeUI(adapter: CarouselAdapter){
        viewModel.promos.observe(viewLifecycleOwner){ promos ->
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