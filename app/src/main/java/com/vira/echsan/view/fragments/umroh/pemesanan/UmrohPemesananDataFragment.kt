package com.vira.echsan.view.fragments.umroh.pemesanan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.vira.echsan.databinding.FragmentUmrohCheckoutPemesananDataBinding
import com.vira.echsan.viewmodel.UmrohSharedViewModel

class UmrohPemesananDataFragment : Fragment(){
    lateinit var sharedViewModel: UmrohSharedViewModel
    lateinit var binding: FragmentUmrohCheckoutPemesananDataBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedViewModel =
            ViewModelProviders.of(requireActivity()).get(UmrohSharedViewModel::class.java)
        binding = FragmentUmrohCheckoutPemesananDataBinding.inflate(inflater, container, false)
        initUI()
        return binding.root
    }

    private fun initUI() {
    }

    companion object{
        fun newInstance(): UmrohPemesananDataFragment {
            return UmrohPemesananDataFragment()
        }
    }
}