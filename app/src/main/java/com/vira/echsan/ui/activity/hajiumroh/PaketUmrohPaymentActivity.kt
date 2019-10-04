package com.vira.echsan.ui.activity.hajiumroh

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.*
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.vira.echsan.R
import com.vira.echsan.helper.SessionManager
import com.vira.echsan.network.ApiClient
import com.vira.echsan.network.ApiRequest
import com.vira.echsan.util.Utils
import com.vira.echsan.util.iosdialog.IOSDialog
import com.vira.echsan.util.permission.PermissionListener
import com.vira.echsan.util.permission.PermissionManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_paket_payment.*
import kotlinx.android.synthetic.main.xml_toolbar.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import pl.aprilapps.easyphotopicker.DefaultCallback
import pl.aprilapps.easyphotopicker.EasyImage
import retrofit2.HttpException
import java.io.File

class PaketUmrohPaymentActivity : AppCompatActivity(), View.OnClickListener{
    private lateinit var dialog: IOSDialog
    private lateinit var sessionManager: SessionManager
    private var id_paket = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paket_payment)
        init()
        id_paket = intent.getStringExtra("id_paket")
        getDetailUmroh(id_paket)
    }

    private fun init() {
        title = "Pembelian"
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

        containerPay.setOnClickListener(this)
        containerVoucher.setOnClickListener(this)
    }


    private fun getDetailUmroh(id_paket: String) {
        showLoading()
        val apiService = ApiClient.getClient().create(ApiRequest::class.java)
        apiService
            .umrohDetail("detail", id_paket)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnTerminate { hideLoading() }
            .subscribe(
                { resp ->
                    hideLoading()
                    when (resp.code) {
                        200 -> {
                            txtPoint.text = resp.data.point + " POIN"
                            txtHarga.text = resp.data.harga
                            txtNamaPaket.text = resp.data.namaPaket

                            Glide.with(this).load(resp.data.icon).into(imgIcon)
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

    fun showLoading() {
        dialog.show()
    }

    fun hideLoading() {
        if (dialog.isShowing)
            dialog.dismiss()
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.containerPay -> {
                val intent = Intent(this, PaketUmrohUploadActivity::class.java)
                intent.putExtra("id_paket", id_paket)
                startActivity(intent)
            }
            R.id.containerVoucher -> {
                val alertDialogBuilder = AlertDialog.Builder(this)
                val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val dialogView = inflater.inflate(R.layout.segment_dialog_kupon, null)
                alertDialogBuilder.setView(dialogView)

                val txtTutup = dialogView.findViewById(R.id.txtTutup) as TextView

                val alertDialog = alertDialogBuilder.create()
                alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                alertDialog.setCanceledOnTouchOutside(true)
                alertDialog.show()

                txtTutup.setOnClickListener {
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
