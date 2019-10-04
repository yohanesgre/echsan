package com.vira.echsan.ui.activity.profile

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import com.bumptech.glide.Glide
import com.vira.echsan.ui.activity.HomepageFirstActivity
import com.vira.echsan.R
import com.vira.echsan.helper.SessionManager
import com.vira.echsan.network.ApiClient
import com.vira.echsan.network.ApiRequest
import com.vira.echsan.ui.activity.auth.LoginActivity
import com.vira.echsan.util.Utils
import com.vira.echsan.util.iosdialog.IOSDialog
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.xml_toolbar.*
import retrofit2.HttpException

class ProfileActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var dialog: IOSDialog
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        init()
        getProfile()
    }


    private fun init() {
        title = "Profile"
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
        setupClick()
    }

    private fun getProfile() {
        showLoading()
        val apiService = ApiClient.getClient().create(ApiRequest::class.java)
        apiService
            .profile(sessionManager.getString(sessionManager.TOKEN))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnTerminate { hideLoading() }
            .subscribe(
                { resp ->
                    hideLoading()
                    when (resp.code) {
                        200 -> {
                            txtNama.text = resp.data.nama
                            txtJenisMember.text = resp.data.jenisMember
                            txtJumlahPoint.text = resp.data.jumlahPoint
                            txtNoMember.text = resp.data.noMember
                            txtKodeReferral.text = resp.data.kodeReferral
                            txtMyVoucher.text = resp.data.myVoucher.toString()
                            txtMyDownlink.text = resp.data.myDownlink.toString()
                            txtMyPoint.text = resp.data.myPoint.toString()
                            txtMyRedeem.text = resp.data.myRedeem.toString()
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

    private fun setupClick() {
        containerProfile.setOnClickListener(this)
        containerNotification.setOnClickListener(this)
        containerHome.setOnClickListener(this)
        containerLogout.setOnClickListener(this)
        containerProfiles.setOnClickListener(this)
        containerBelanjaProduk.setOnClickListener(this)
        containerRedeemPoin.setOnClickListener(this)
        containerJaringanMember.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.containerHome -> {
                val intent = Intent(this, HomepageFirstActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
                finish()
            }
            R.id.containerProfile -> {
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
            }
            R.id.containerNotification -> {

            }
            R.id.containerLogout -> {
                sessionManager.destroySession()
                val intent = Intent(this, HomepageFirstActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
                finish()
            }
            R.id.containerProfiles -> {
                val intent = Intent(this, DataDiriActivity::class.java)
                startActivity(intent)
            }
            R.id.containerRedeemPoin -> {
                val intent = Intent(this, RedeemPoinActivity::class.java)
                startActivity(intent)
            }
            R.id.containerJaringanMember -> {
                val intent = Intent(this, DownlineActivity::class.java)
                startActivity(intent)
            }
            R.id.containerBelanjaProduk -> {

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
