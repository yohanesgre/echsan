package com.vira.echsan

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vira.echsan.databinding.ActivityMainBinding
import com.vira.echsan.features.home.HomeFragment
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector{

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    override fun supportFragmentInjector() = dispatchingAndroidInjector

    var lastFragmentTag: Int = -1
    var userId: Int? = null
    private lateinit var binding: ActivityMainBinding

    companion object {
        const val PARAM_NAVIGATION_ID = "navigation_id"

        fun newInstance(context: Context, navigationId: Int) =  Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra(PARAM_NAVIGATION_ID, navigationId)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userId = intent.getIntExtra("UserID", 0)
        println("UserID MainActivity: $userId")
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.navbarBottom.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        val navigationId = intent.getIntExtra(PARAM_NAVIGATION_ID, R.id.fragment_home)
        binding.navbarBottom.selectedItemId = navigationId
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item->
        loadFragment(item.itemId)
        true
    }

    private fun loadFragment(itemId: Int) {
        val tag = itemId.toString()
        val fragment = supportFragmentManager.findFragmentByTag(tag) ?: when (itemId) {
            R.id.fragment_home -> {
                lastFragmentTag = itemId
                HomeFragment()
            }
            R.id.fragment_bookings -> {
                /*lastFragmentTag = itemId
                BookingsFragment().apply {
                    arguments = Bundle().apply {
                        putInt("UserID", this@MainActivity.userId!!)
                    }
                }*/
                null
            }
            R.id.fragment_points -> {
                null

            }
            R.id.fragment_profile-> {
                null
            }
            else -> {
                null
            }
        }

        if (fragment != null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.containter, fragment as Fragment, tag)
                .commit()
        } else {
            showAlertDevelopment()
        }
    }

    private fun showAlertDevelopment() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Sedang dalam Pengembangan")
            .setMessage("Silakan gunakan fitur lain yang ada...")
            .setCancelable(true)
            .setPositiveButton("OK") { dialog, id ->
                dialog.dismiss()
            }
            .create()
            .show()

    }
}