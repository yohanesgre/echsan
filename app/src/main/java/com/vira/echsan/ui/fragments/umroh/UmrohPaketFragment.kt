package com.vira.echsan.ui.fragments.umroh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import com.vira.echsan.R
import com.vira.echsan.databinding.FragmentUmrohPaketBinding
import com.vira.echsan.di.Injectable
import com.vira.echsan.di.injectViewModel
import com.vira.echsan.ui.fragments.umroh.paket.UmrohPaketDetilFragment
import com.vira.echsan.ui.fragments.umroh.paket.UmrohPaketFasilitasFragment
import com.vira.echsan.ui.fragments.umroh.paket.UmrohPaketPenerbanganFragment
import com.vira.echsan.ui.fragments.umroh.paket.UmrohPaketTanggalFragment
import com.vira.echsan.viewmodel.PaketUmrohSharedViewModel
import com.vira.echsan.viewmodel.UmrohViewModel
import javax.inject.Inject

class UmrohPaketFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: UmrohViewModel
    private lateinit var sharedViewModel: PaketUmrohSharedViewModel
    private lateinit var binding: FragmentUmrohPaketBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = injectViewModel(viewModelFactory)
        sharedViewModel =
            ViewModelProviders.of(requireActivity()).get(PaketUmrohSharedViewModel::class.java)
        binding = FragmentUmrohPaketBinding.inflate(inflater, container, false)
        context ?: return binding.root
        initUI()
        return binding.root
    }

    private fun initUI(){
        addFragment()
        requireActivity().onBackPressedDispatcher.addCallback(this@UmrohPaketFragment){
            sharedViewModel.searchPaket.observe(viewLifecycleOwner){
                val nav =
                    UmrohPaketFragmentDirections.actionFragmentUmrohPaketToFragmentUmrohHasil(
                        it[0],
                        it[1],
                        it[2]
                    )
                binding.root.findNavController().navigate(nav)
            }
        }
    }

    private fun addFragment(){
        val fragmentManager = childFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.add(R.id.container_fragment_detil,
            UmrohPaketDetilFragment.newInstance()
        )
        transaction.add(R.id.container_fragment_pilih_tanggal,
            UmrohPaketTanggalFragment.newInstance()
        )
        transaction.add(R.id.container_fragment_fasilitas,
            UmrohPaketFasilitasFragment.newInstance()
        )
        transaction.add(R.id.container_fragment_penerbangan,
            UmrohPaketPenerbanganFragment.newInstance()
        )
        transaction.commit()
    }
}