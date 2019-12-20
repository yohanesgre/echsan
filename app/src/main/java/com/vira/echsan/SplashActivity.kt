package com.vira.echsan

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vira.echsan.databinding.ActivityAuthSplashBinding
import com.vira.echsan.di.Injectable
import com.vira.echsan.di.injectViewModel
import com.vira.echsan.features.viewmodel.LoginViewModel
import javax.inject.Inject

class SplashActivity : AppCompatActivity(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = injectViewModel(viewModelFactory)
        val binding: ActivityAuthSplashBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_auth_splash)
        viewModel.getProfile.observe(this, Observer {
            Handler().postDelayed({
                if (it != null) {
                    println("UserID: ${it.id}")
                    val intent = Intent(this, MainActivity::class.java).apply {
                        putExtra("UserID", it.id)
                    }
                    startActivity(intent)
                } else {
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                }
                finish()
            }, 2000L)
        })
    }
}