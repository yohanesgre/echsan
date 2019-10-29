package com.vira.echsan.ui.fragments.bookings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.vira.echsan.databinding.FragmentBookingsChildBinding
import com.vira.echsan.ui.adapters.BookingAdapter
import com.vira.echsan.ui.viewmodel.BookingsChildViewModel
import com.vira.echsan.utils.InjectorUtils


class BookingsChildFragment : Fragment(), View.OnClickListener {
    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        position = arguments?.getInt(POSITION_KEY) ?: 0
    }

    private val viewModel: BookingsChildViewModel by viewModels{
        InjectorUtils.provideBookingsChieldViewModelFactory(requireContext(), position)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?): View? {
        val binding = FragmentBookingsChildBinding
            .inflate(inflater, container, false)

        context?: return binding.root

        val adapter = BookingAdapter()
        subcribeUI(adapter)

        binding.rvContentBookings.apply {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
            this.adapter = adapter
        }

        return binding.root
    }

    fun subcribeUI(adapter: BookingAdapter){
        viewModel.bookingByStatus.observe(viewLifecycleOwner){
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
