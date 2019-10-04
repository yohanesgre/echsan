package com.vira.echsan.ui.activity.auth

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import com.vira.echsan.ui.activity.HomepageFirstActivity
import com.vira.echsan.R
import kotlinx.android.synthetic.main.activity_auth_welcome.*

class WelcomeActivity : Activity() {
    private val SPLASH_DISPLAY_LENGTH = 1000
    private val SPLASH_DISPLAY_SHORT = 1000
    private val handler = Handler()
    private var strValue: String? = null
    private var progressStatus = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth_welcome)
        init()
    }

    private fun init() {
        val conMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (conMgr.activeNetworkInfo == null) {
            Thread(Runnable {
                while (progressStatus < 100) {
                    progressStatus += 2
                    strValue = progressStatus.toString()
                    txtValuePercentage.post { txtValuePercentage.setText(strValue + "%") }
                    try {
                        Thread.sleep(50)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    //handler.post { mProgressBar.setProgress(progressStatus) }
                }
            }).start()
            Handler().postDelayed({
                val intent = Intent(this@WelcomeActivity, HomepageFirstActivity::class.java)
                this@WelcomeActivity.startActivity(intent)
                this@WelcomeActivity.finish()
            }, SPLASH_DISPLAY_SHORT.toLong())

        } else {
            Thread(Runnable {
                while (progressStatus < 100) {
                    progressStatus += 2
                    strValue = progressStatus.toString()
                    txtValuePercentage.post { txtValuePercentage.setText(strValue + "%") }
                    try {
                        Thread.sleep(50)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    //handler.post{ mProgressBar.setProgress(progressStatus) }
                }
            }).start()
            Handler().postDelayed({
                val intent = Intent(this@WelcomeActivity, HomepageFirstActivity::class.java)
                this@WelcomeActivity.startActivity(intent)
                this@WelcomeActivity.finish()
            }, SPLASH_DISPLAY_LENGTH.toLong())
        }
    }


}
