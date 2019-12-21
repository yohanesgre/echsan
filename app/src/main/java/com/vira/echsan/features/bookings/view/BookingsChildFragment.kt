package com.vira.echsan.features.bookings.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.vira.echsan.databinding.FragmentBookingsChildBinding
import com.vira.echsan.di.Injectable
import com.vira.echsan.di.injectViewModel
import com.vira.echsan.features.bookings.BookingAdapter
import com.vira.echsan.features.bookings.viewmodel.BookingsChildViewModel
import javax.inject.Inject


class BookingsChildFragment : Fragment(), Injectable, View.OnClickListener {
    private var position: Int = 0
    private var userId: Int = 0
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: BookingsChildViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?): View? {
        position = arguments?.getInt(POSITION_KEY) ?: 0
        userId = arguments?.getInt("UserID") ?: 0
        viewModel = injectViewModel(viewModelFactory)
        viewModel.position.postValue(position)
        viewModel.userId = userId
        viewModel.isDone = position == 0
        val binding = FragmentBookingsChildBinding
            .inflate(inflater, container, false)
        val adapter = BookingAdapter()
        subcribeUI(adapter)
        binding.rvContentBookings.apply {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
            this.adapter = adapter
        }
        return binding.root
    }

    fun subcribeUI(adapter: BookingAdapter){
        viewModel.bookings.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun onClick(v: View) {
        Toast.makeText(v.context, "Clicked Position: $position", Toast.LENGTH_LONG).show()
    }

    companion object {
        const val POSITION_KEY = "FragmentPositionKey"
        fun newInstance(args: Bundle): BookingsChildFragment = BookingsChildFragment().apply { arguments = args }
    }
}
