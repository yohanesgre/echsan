package com.vira.echsan.view.fragments.umroh.paket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.vira.echsan.R
import com.vira.echsan.adapters.umroh.PaketRencanaPerjalananAdapter
import com.vira.echsan.databinding.DialogUmrohPaketRencanaPerjalananBinding
import com.vira.echsan.ui.fragments.umroh.UmrohPaketFilterDialog
import com.vira.echsan.viewmodel.PaketUmrohSharedViewModel

class UmrohPaketRencanaPerjalananDialog : DialogFragment(){
    lateinit var binding: DialogUmrohPaketRencanaPerjalananBinding
    lateinit var sharedViewModel: PaketUmrohSharedViewModel
    lateinit var adapter:PaketRencanaPerjalananAdapter

    companion object{
        val TAG:String = "Paket Umroh Rencana Perjalanan Dialog"
        fun display(fragmentManager: FragmentManager): UmrohPaketRencanaPerjalananDialog {
            val dialog = UmrohPaketRencanaPerjalananDialog()
            dialog.show(fragmentManager,
                TAG
            )
            return dialog
        }
    }

    override fun onStart() {
        super.onStart()
        var dialog = getDialog()
        if (dialog!=null){
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window?.setLayout(width, height)
            dialog.window?.setWindowAnimations(R.style.AppTheme_Slide)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedViewModel = ViewModelProviders.of(requireActivity()).get(PaketUmrohSharedViewModel::class.java)
        binding = DialogUmrohPaketRencanaPerjalananBinding.inflate(inflater, container, false)
        initUI()
        return binding.root
    }

    private fun initUI(){
        adapter = PaketRencanaPerjalananAdapter()
        binding.toolbar.title = "Rencana Perjalanan"
        binding.toolbar.setNavigationOnClickListener {dismiss()}
        adapter.submitList(sharedViewModel.listOfRencanaPerjalanan)
        binding.rvRencanaPerjalanan.adapter = adapter
        binding.rvRencanaPerjalanan.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }
}