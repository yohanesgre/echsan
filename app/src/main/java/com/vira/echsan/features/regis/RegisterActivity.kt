package com.vira.echsan.features.regis

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.vira.echsan.R
import com.vira.echsan.data.Result
import com.vira.echsan.databinding.ActivityAuthSignupBinding
import com.vira.echsan.di.Injectable
import com.vira.echsan.di.injectViewModel
import com.vira.echsan.features.MainActivity
import com.vira.echsan.features.regis.viewmodel.RegisterViewModel
import javax.inject.Inject

class RegisterActivity : AppCompatActivity(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: RegisterViewModel

    private lateinit var binding: ActivityAuthSignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = injectViewModel(viewModelFactory)
        binding =
            DataBindingUtil.setContentView(
                this,
                R.layout.activity_auth_signup
            )
        binding.btnSignup.setOnClickListener {
            register()
        }
        subscribeUI()
    }

    fun register() {
        viewModel._loginInfo.value = listOf(
            binding.inputRegisName.text.toString(),
            binding.inputRegisEmail.text.toString(),
            binding.inputRegisPhone.text.toString(),
            binding.inputRegisNik.text.toString()
        )
    }

    private fun subscribeUI() {
        viewModel.getRegisterResult.observe(this, Observer { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    if (result.data != null) {
                        val intent = Intent(this, MainActivity::class.java).apply {
                            result.data.apply {
                                putExtra("UserID", this.id)
                            }
                        }
                        startActivity(intent)
                    }
                }
                Result.Status.LOADING -> {
                }
                Result.Status.ERROR -> {
                    Snackbar.make(binding.root, result.message!!, Snackbar.LENGTH_LONG).show()
                }
            }
        })
    }
}