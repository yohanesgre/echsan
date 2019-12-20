package com.vira.echsan.features.umroh.view

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.vira.echsan.databinding.FragmentUmrohInputJamaahBinding
import com.vira.echsan.di.Injectable
import com.vira.echsan.features.umroh.viewmodel.UmrohSharedViewModel
import com.vira.echsan.utils.CalendarHelper
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class UmrohPaketInputJamaahFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var sharedViewModel: UmrohSharedViewModel
    private lateinit var binding: FragmentUmrohInputJamaahBinding
    private val args: UmrohPaketInputJamaahFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedViewModel =
            ViewModelProviders.of(requireActivity()).get(UmrohSharedViewModel::class.java)
        binding = FragmentUmrohInputJamaahBinding.inflate(inflater, container, false)
        Snackbar.make(binding.root, "Position ${args.jamaahOrder}", Snackbar.LENGTH_SHORT).show()
        context ?: return binding.root
        initUI()
        return binding.root
    }

    private fun submitDataJamaah() {
        binding.setOnClickLanjut {
            val index = sharedViewModel.mInputJamaah.jamaahOrder?.indexOf(args.jamaahOrder)
            if (args.jamaahOrder != index) {
                sharedViewModel.insertJamaah(
                    args.jamaahOrder,
                    binding.inputNamaLengkap.text.toString(),
                    "L",
                    "Test",
                    "${binding.inputTglLahirTahun.text.toString()}-${binding.inputTglLahirBln.text.toString()}-${binding.inputTglLahirHari.text.toString()}",
                    "TestAlamat",
                    1,
                    2,
                    "Keluarahn Test",
                    "District test",
                    "City test",
                    "Province test",
                    123456,
                    binding.inputNomerTelepon.text.toString()
                )
                val nav =
                    UmrohPaketInputJamaahFragmentDirections.actionFragmentUmrohPaketInputJamaahToFragmentUmrohPaketPemesanan()
                binding.root.findNavController().navigate(nav)
            }
        }
    }

    private fun initUI() {
        binding.toolbar.title = "PEMBAYARAN"
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayShowHomeEnabled(true)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        requireActivity().onBackPressedDispatcher.addCallback(this@UmrohPaketInputJamaahFragment) {
            val nav =
                UmrohPaketInputJamaahFragmentDirections.actionFragmentUmrohPaketInputJamaahToFragmentUmrohPaketPemesanan()
            binding.root.findNavController().navigate(nav)
        }
        binding.inputTglLahirHari.isFocusable = true
        binding.inputTglLahirHari.isFocusableInTouchMode = true
        binding.inputTglLahirHari.inputType = InputType.TYPE_NULL
        binding.inputTglLahirBln.isFocusable = true
        binding.inputTglLahirBln.isFocusableInTouchMode = true
        binding.inputTglLahirBln.inputType = InputType.TYPE_NULL
        binding.inputTglLahirTahun.isFocusable = true
        binding.inputTglLahirTahun.isFocusableInTouchMode = true
        binding.inputTglLahirTahun.inputType = InputType.TYPE_NULL
        showDatePicker()
        if (sharedViewModel.mInputJamaah.jamaahOrder?.size != 0) {
            val index = sharedViewModel.mInputJamaah.jamaahOrder?.indexOf(args.jamaahOrder)
            if (args.jamaahOrder == index) {
                val index = sharedViewModel.mInputJamaah.jamaahOrder!!.indexOf(args.jamaahOrder)
                binding.inputNamaLengkap.setText(sharedViewModel.mInputJamaah.fullName?.get(index))
                val date = sharedViewModel.mInputJamaah.birthDate?.get(index)?.split("-".toRegex())
                binding.inputTglLahirTahun.setText(date?.get(0))
                binding.inputTglLahirBln.setText(date?.get(1))
                binding.inputTglLahirHari.setText(date?.get(2))
                binding.inputNomerTelepon.setText(sharedViewModel.mInputJamaah.phone?.get(index))
            }
        }
        if (binding.cbKamarFirst.isSelected) {
            binding.cbKamarSecond.isChecked = false
            binding.cbKamarThird.isChecked = false
        } else if (binding.cbKamarSecond.isSelected) {
            binding.cbKamarFirst.isChecked = false
            binding.cbKamarThird.isChecked = false
        } else if (binding.cbKamarThird.isSelected) {
            binding.cbKamarFirst.isSelected = false
            binding.cbKamarThird.isSelected = false
        }
        submitDataJamaah()
    }

    fun showDatePicker() {
        // DatePicker
        var cal = Calendar.getInstance()

        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val myFormat = "yyyy-MM-dd" // mention the format you need
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                val time = sdf.format(cal.time).split("-".toRegex())
                binding.inputTglLahirTahun.setText(time[0])
                binding.inputTglLahirBln.setText(time[1])
                binding.inputTglLahirHari.setText(time[2])
            }

        val dialog = DatePickerDialog(
            requireContext(), dateSetListener,
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)
        )

        binding.inputTglLahirHari.setOnClickListener {
            Log.d("Clicked", "Interview Date Clicked")
            dialog.datePicker.maxDate = CalendarHelper.getCurrentDateInMills()
            dialog.show()
        }
        binding.inputTglLahirBln.setOnClickListener {
            Log.d("Clicked", "Interview Date Clicked")
            dialog.datePicker.maxDate = CalendarHelper.getCurrentDateInMills()
            dialog.show()
        }
        binding.inputTglLahirTahun.setOnClickListener {
            Log.d("Clicked", "Interview Date Clicked")
            dialog.datePicker.maxDate = CalendarHelper.getCurrentDateInMills()
            dialog.show()
        }
    }
}