package com.vira.echsan.ui.fragments.umroh.paket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.vira.echsan.databinding.FragmentUmrohPaketDetilBinding
import com.vira.echsan.viewmodel.PaketUmrohViewModel

class UmrohPaketDetilFragment : Fragment(){

    lateinit var sharedViewModel: PaketUmrohViewModel
    lateinit var binding:FragmentUmrohPaketDetilBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedViewModel =
            ViewModelProviders.of(requireParentFragment()).get(PaketUmrohViewModel::class.java)
        binding = FragmentUmrohPaketDetilBinding.inflate(inflater, container, false)
        initUI()
        return binding.root
    }

    private fun initUI() {
        /*sharedViewModel.paketUmroh.observe(requireParentFragment().viewLifecycleOwner){

        }*/
    }

    companion object{
        fun newInstance(): UmrohPaketDetilFragment {
            return UmrohPaketDetilFragment()
        }
    }
}