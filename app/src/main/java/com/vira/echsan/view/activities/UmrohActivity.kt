package com.vira.echsan.ui.activities.umroh_haji

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.vira.echsan.R
import com.vira.echsan.databinding.ActivityUmrohBinding
import com.vira.echsan.di.injectViewModel
import com.vira.echsan.viewmodel.UmrohViewModel
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class UmrohActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityUmrohBinding>(this, R.layout.activity_umroh)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}