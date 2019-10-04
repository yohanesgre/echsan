package com.vira.echsan.ui.activity.auth

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.vira.echsan.ui.activity.PanduanDoaActivity
import com.vira.echsan.R
import com.vira.echsan.helper.SessionManager
import com.vira.echsan.network.*
import com.vira.echsan.util.Utils
import com.vira.echsan.util.iosdialog.IOSDialog
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_auth_login.*
import retrofit2.HttpException

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var sessionManager: SessionManager
    private lateinit var dialog: IOSDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth_login)
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
        txtLogin.setOnClickListener(this)
        txtSignUp.setOnClickListener(this)
    }

    private fun doLogin() {
        showLoading()
        val apiService = ApiClient.getClient().create(ApiRequest::class.java)
        apiService.login(etUsername.text.toString(), etPassword.text.toString())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnTerminate { hideLoading() }
            .subscribe(
                { resp ->
                    hideLoading()
                    when (resp.code) {
                        200 -> {
                            val intent = Intent(this, WelcomeActivity::class.java)
                            startActivity(intent)
                            finish()
                            sessionManager.setLogin()
                            sessionManager.storeString(sessionManager.TOKEN, resp.data.token)
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
            R.id.txtLogin -> {
                doLogin()
            }
            R.id.txtSignUp -> {
                val intent = Intent(this, SignUpActivity::class.java)
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
