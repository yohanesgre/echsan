package com.vira.echsan.ui.activity.hajiumroh

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import com.vira.echsan.R
import com.vira.echsan.adapter.PaketUmrohAdapter
import com.vira.echsan.helper.SessionManager
import com.vira.echsan.model.ResUmroh
import com.vira.echsan.network.ApiClient
import com.vira.echsan.network.ApiRequest
import com.vira.echsan.util.EndlessRecyclerViewScrollListener
import com.vira.echsan.util.Utils
import com.vira.echsan.util.iosdialog.IOSDialog
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_paket_haji.*
import kotlinx.android.synthetic.main.xml_recycler.*
import kotlinx.android.synthetic.main.xml_toolbar.*
import retrofit2.HttpException
import java.util.*

class PaketHajiActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var dialog: IOSDialog
    private lateinit var sessionManager: SessionManager
    private lateinit var adapter: PaketUmrohAdapter
    private var mList: ArrayList<ResUmroh> = ArrayList()
    private lateinit var scrollListener: EndlessRecyclerViewScrollListener
    private var times = 0
    private var sort = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paket_haji)
        init()
        initRecycler()
        getHaji("0", "20", "", sort)
    }

    private fun init() {
        title = "Paket Haji"
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        sessionManager = SessionManager(this)
        dialog = IOSDialog.Builder(this)
            .setSpinnerColorRes(R.color.white)
            .setMessageColorRes(R.color.white)
            .setTitle("Loading...")
            .setTitleColorRes(R.color.white)
            .setMessageContent("Please wait!")
            .setCancelable(false)
            .setMessageContentGravity(Gravity.END)
            .build()
        containerSort.setOnClickListener(this)
        containerFilter.setOnClickListener(this)
    }


    private fun initRecycler() {
        recycler.setHasFixedSize(true)
        val mLayoutManager = LinearLayoutManager(this)
        recycler.setLayoutManager(mLayoutManager)
        adapter = PaketUmrohAdapter(this)
        recycler.adapter = adapter

        scrollListener =
            object :
                EndlessRecyclerViewScrollListener(recycler.getLayoutManager() as LinearLayoutManager) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {

                    getHaji((times * 20).toString(), "20", "", "DESC")
                }
            }
        recycler.addOnScrollListener(scrollListener)
    }

    private fun getHaji(start: String, length: String, search: String, sorting: String) {
        showLoading()
        val apiService = ApiClient.getClient().create(ApiRequest::class.java)
        apiService
            .haji("list", start, length, search, sorting)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnTerminate { hideLoading() }
            .subscribe(
                { resp ->
                    hideLoading()
                    when (resp.code) {
                        200 -> {
                            updateList(resp.data.res)
                        }
                    }
                },
                { error ->
                    hideLoading()
                    Log.e("Error", error.message)
                    try {
                        var code: Int = (error as HttpException).code()
                        if (code.equals(401)) {
                            Utils.showToast(this, "Username atau Password Anda salah!")
                        } else if (code.equals(404)) {
                            Utils.showToast(this, "Not Found")
                        } else {
                            Utils.showToast(this, "Uknown Error")
                        }
                    } catch (e: Exception) {
                        Utils.showToast(this, "Tidak dapat terhubung dengan server")
                    }
                }
            )
    }


    fun updateList(resList: List<ResUmroh>) {
        if (resList.size > 0) {
            mList.addAll(resList)
            adapter.updateData(mList)
            adapter.notifyDataSetChanged()
            times++
        } else {
            if (times == 0) {
                recycler.visibility = View.GONE
                Toast.makeText(this, "Tidak ada data ditemukan", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun showLoading() {
        dialog.show()
    }

    fun hideLoading() {
        if (dialog.isShowing)
            dialog.dismiss()
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.containerSort -> {
                val alertDialogBuilder = AlertDialog.Builder(this)
                val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val dialogView = inflater.inflate(R.layout.segment_dialog_sorting, null)
                alertDialogBuilder.setView(dialogView)

                val txtTerapkan = dialogView.findViewById(R.id.txtTerapkan) as TextView
                val rbtnAscending = dialogView.findViewById(R.id.rbtnAscending) as RadioButton
                val rbtnDescending = dialogView.findViewById(R.id.rbtnDescending) as RadioButton

                val alertDialog = alertDialogBuilder.create()
                alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                alertDialog.setCanceledOnTouchOutside(true)
                alertDialog.show()

                txtTerapkan.setOnClickListener {
                    if(rbtnAscending.isChecked) {
                        sort = "ASC"
                    } else if(rbtnDescending.isChecked) {
                        sort = "DESC"
                    } else {
                        sort = ""
                    }
                    mList.clear()
                    getHaji("0", "20", "", sort)
                    alertDialog.dismiss()
                }
            }
            R.id.containerFilter -> {
                val alertDialogBuilder = AlertDialog.Builder(this)
                val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val dialogView = inflater.inflate(R.layout.segment_dialog_filter, null)
                alertDialogBuilder.setView(dialogView)

                val txtTerapkan = dialogView.findViewById(R.id.txtTerapkan) as TextView

                val alertDialog = alertDialogBuilder.create()
                alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                alertDialog.setCanceledOnTouchOutside(true)
                alertDialog.show()

                txtTerapkan.setOnClickListener {
                    alertDialog.dismiss()
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
