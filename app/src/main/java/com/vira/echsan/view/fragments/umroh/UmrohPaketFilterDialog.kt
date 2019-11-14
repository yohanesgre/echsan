package com.vira.echsan.ui.fragments.umroh

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.jaygoo.widget.OnRangeChangedListener
import com.jaygoo.widget.RangeSeekBar
import com.vira.echsan.R
import kotlinx.android.synthetic.main.dialog_umroh_paket_filter.*
import kotlinx.android.synthetic.main.dialog_umroh_paket_filter.view.*


class UmrohPaketFilterDialog : DialogFragment() {
    var maxVal:String = "0"
    var minVal:String = "0"

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
        return  inflater.inflate(R.layout.dialog_umroh_paket_filter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.toolbar.setNavigationOnClickListener {
            dismiss()
        }
        view.toolbar.title = "Filter"
        view.toolbar_title.setText(view.toolbar.title)
        initRecyclerView()
        initRangeSeekBar()
        initInputText()
    }

    fun initInputText(){
        minVal = String.format("%.0f", rangeSeekbar_filter.leftSeekBar.progress)
        maxVal = String.format("%.0f", rangeSeekbar_filter.rightSeekBar.progress)
        input_filter_harga_min.setText(minVal)
        input_filter_harga_max.setText(maxVal)

        input_filter_harga_min.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString().toFloat() <= rangeSeekbar_filter.maxProgress && s.toString().toFloat() >= rangeSeekbar_filter.minProgress ){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        input_filter_harga_min.setTextColor(activity!!.getColor(R.color.black))
                    }
                    minVal = s.toString()
                    updateProgressSeekBar()
                }else{
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        input_filter_harga_min.setTextColor(activity!!.getColor(R.color.red))
                    }
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s == ""){
                    minVal = "0"
                    input_filter_harga_min.setText("0")
                }
            }
        })

        input_filter_harga_max.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString().toFloat() <= rangeSeekbar_filter.maxProgress && s.toString().toFloat() >= rangeSeekbar_filter.minProgress ){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        input_filter_harga_max.setTextColor(activity!!.getColor(R.color.black))
                    }
                    maxVal = s.toString()
                    updateProgressSeekBar()
                }else{
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        input_filter_harga_max.setTextColor(activity!!.getColor(R.color.red))
                    }
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s == ""){
                    maxVal = "0"
                    input_filter_harga_max.setText("0")
                }
            }
        })
    }

    fun updateProgressSeekBar(){
        rangeSeekbar_filter.setProgress(minVal.toFloat(), maxVal.toFloat())
    }

    fun initRecyclerView(){
        rv_filter_durasi.layoutManager = LinearLayoutManager(activity!!, LinearLayoutManager.HORIZONTAL, false)
        rv_filter_lokasi.layoutManager = LinearLayoutManager(activity!!, LinearLayoutManager.HORIZONTAL, false)

        val snapHelperDurasi: SnapHelper = LinearSnapHelper()
        snapHelperDurasi.attachToRecyclerView(rv_filter_durasi)
        val snapHelperLokasi: SnapHelper = LinearSnapHelper()
        snapHelperLokasi.attachToRecyclerView(rv_filter_lokasi)
    }

    fun initRangeSeekBar(){
        rangeSeekbar_filter.setProgress(0F, 100F)
        rangeSeekbar_filter.setRange(2500000F, 50000000F, 1000F)
        rangeSeekbar_filter.setIndicatorTextDecimalFormat("0")
        rangeSeekbar_filter.setOnRangeChangedListener(object : OnRangeChangedListener {
            override fun onRangeChanged(
                view: RangeSeekBar,
                leftValue: Float,
                rightValue: Float,
                isFromUser: Boolean
            ) {

            }

            override fun onStartTrackingTouch(view: RangeSeekBar, isLeft: Boolean) {
                //start tracking touch
            }

            override fun onStopTrackingTouch(view: RangeSeekBar, isLeft: Boolean) {
                val t = Toast.makeText(activity!!.applicationContext,
                    "Max: ${String.format("%.0f", view.rightSeekBar.progress)}\nMin: ${String.format("%.0f", view.leftSeekBar.progress)}", Toast.LENGTH_SHORT)
                t.show()
                minVal = String.format("%.0f", view.leftSeekBar.progress)
                maxVal = String.format("%.0f", view.rightSeekBar.progress)
                input_filter_harga_min.setText(minVal)
                input_filter_harga_max.setText(maxVal)
            }
        })
    }

    companion object{
        val TAG:String = "Paket Umroh Filter Dialog"
        fun display(fragmentManager: FragmentManager): UmrohPaketFilterDialog {
            val dialog = UmrohPaketFilterDialog()
            dialog.show(fragmentManager,
                TAG
            )
            return dialog
        }
    }
}