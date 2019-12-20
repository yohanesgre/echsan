package com.vira.echsan

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.vira.echsan.data.Result
import com.vira.echsan.databinding.ActivityAuthLoginBinding
import com.vira.echsan.di.Injectable
import com.vira.echsan.di.injectViewModel
import com.vira.echsan.features.viewmodel.LoginViewModel
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = injectViewModel(viewModelFactory)
        val binding: ActivityAuthLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_auth_login)
        binding.btnLogin.setOnClickListener {
            login(binding)
        }
        /*viewModel.getProfile.observe(this, Observer{
            println("Debug GetProfile: $it")
        })*/
        viewModel.getProfileResult.observe(this, Observer { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    if (result.data != null) {
                        Snackbar.make(
                            binding.root,
                            "Berhasil login: " + result.data.email,
                            Snackbar.LENGTH_LONG
                        ).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    } else
                        Snackbar.make(
                            binding.root,
                            "Email/Password Anda salah!",
                            Snackbar.LENGTH_LONG
                        ).show()
                }
                Result.Status.LOADING -> {
                }
                Result.Status.ERROR -> {
                    Snackbar.make(binding.root, result.message!!, Snackbar.LENGTH_LONG).show()
                }
            }
        })
    }

    fun login(binding: ActivityAuthLoginBinding) {
        viewModel._loginInfo.value = listOf(
            binding.inputEtLoginMail.text.toString(),
            binding.inputEtLoginPass.text.toString()
        )
    }
}