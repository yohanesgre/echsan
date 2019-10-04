package com.vira.echsan.ui.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import com.vira.echsan.R
import com.vira.echsan.helper.SessionManager
import com.vira.echsan.network.ApiClient
import com.vira.echsan.network.ApiRequest
import com.vira.echsan.ui.activity.auth.LoginActivity
import com.vira.echsan.ui.activity.auth.WelcomeActivity
import com.vira.echsan.ui.activity.ebenefit.EBenefitActivity
import com.vira.echsan.ui.activity.esosial.ESosialActivity
import com.vira.echsan.ui.activity.emember.EMemberActivity
import com.vira.echsan.ui.activity.hajiumroh.PaketActivity
import com.vira.echsan.ui.activity.profile.ProfileActivity
import com.vira.echsan.util.Utils
import com.vira.echsan.util.iosdialog.IOSDialog
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_auth_login.*
import kotlinx.android.synthetic.main.activity_homepage_first.*
import retrofit2.HttpException

class HomepageFirstActivity : AppCompatActivity(), View.OnClickListener  {
    private lateinit var sessionManager: SessionManager
    private lateinit var dialog: IOSDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage_first)
        init()
    }

    private fun init() {
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
        if(sessionManager.getString(sessionManager.TOKEN).equals("", true)) {
            Log.d("vira","Belum login")
        } else {
            getDashboard()
        }
        setupClick()
    }

    private fun setupClick() {
        containerHajiUmroh.setOnClickListener(this)
        containerESosial.setOnClickListener(this)
        containerETravel.setOnClickListener(this)
        containerEMember.setOnClickListener(this)
        containerEBenefit.setOnClickListener(this)
        containerECommerce.setOnClickListener(this)
        containerDoaPanduan.setOnClickListener(this)
        containerTentangKami.setOnClickListener(this)
        containerMediaSosial.setOnClickListener(this)
        containerProfile.setOnClickListener(this)
        containerNotification.setOnClickListener(this)
        containerHome.setOnClickListener(this)
    }

    private fun getDashboard() {
        showLoading()
        val apiService = ApiClient.getClient().create(ApiRequest::class.java)
        apiService.dashboard(sessionManager.getString(sessionManager.TOKEN))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnTerminate { hideLoading() }
            .subscribe(
                { resp ->
                    hideLoading()
                    when (resp.code) {
                        200 -> {
                            txtPoint.text = resp.data.totalPoint + " Point"
                            txtVoucher.text = resp.data.totalVoucher + " Voucher"
                        }
                    }
                },
                { error ->
                    hideLoading()
                    Log.e("Error", error.message)
                    Utils.showToast(this, Utils.fetchErrorMessage(error))
                    try {
                        var code: Int = (error as HttpException).code()
                        if (code.equals(401)) {
                            Utils.showToast(this, "Username atau Password Anda salah!")
                        } else if (code.equals(404)) {
                            Utils.showToast(this, "Uknown Error")
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
            R.id.containerProfile -> {
                if(sessionManager.getString(sessionManager.TOKEN).equals("", true)) {
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                } else {
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                }
            }
            R.id.containerNotification -> {

            }
            R.id.containerHajiUmroh -> {
                val intent = Intent(this, PaketActivity::class.java)
                startActivity(intent)
            }
            R.id.containerHome -> {
                /*val intent = Intent(this, HomepageSecondActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
                finish()*/
            }
            R.id.containerESosial -> {
                val intent = Intent(this, ESosialActivity::class.java)
                startActivity(intent)
            }
            R.id.containerETravel -> {
                val intent = Intent(this, TravelActivity::class.java)
                startActivity(intent)
            }
            R.id.containerEMember -> {
                val intent = Intent(this, EMemberActivity::class.java)
                startActivity(intent)
            }
            R.id.containerEBenefit -> {
                val intent = Intent(this, EBenefitActivity::class.java)
                startActivity(intent)
            }
            R.id.containerECommerce -> {
                val intent = Intent(this, TravelActivity::class.java)
                startActivity(intent)
            }
            R.id.containerDoaPanduan -> {
                val intent = Intent(this, PanduanDoaActivity::class.java)
                startActivity(intent)
            }
            R.id.containerTentangKami -> {
                val intent = Intent(this, TentangKamiActivity::class.java)
                startActivity(intent)
            }
            R.id.containerMediaSosial -> {
                val intent = Intent(this, MediaSosialActivity::class.java)
                startActivity(intent)
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
