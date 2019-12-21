package com.vira.echsan.features

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vira.echsan.R
import com.vira.echsan.databinding.ActivityMainBinding
import com.vira.echsan.features.bookings.view.BookingsFragment
import com.vira.echsan.features.home.HomeFragment
import com.vira.echsan.features.login.LoginActivity
import com.vira.echsan.features.profile.ProfileActivity
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
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
        binding.navbarBottom.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        val navigationId = intent.getIntExtra(
            PARAM_NAVIGATION_ID,
            R.id.fragment_home
        )
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
                HomeFragment().apply {
                    arguments = Bundle().apply {
                        putInt("UserID", this@MainActivity.userId!!)
                    }
                }
            }
            R.id.fragment_bookings -> {
                if (userId!! != 0) {
                    lastFragmentTag = itemId
                    BookingsFragment().apply {
                        arguments = Bundle().apply {
                            putInt("UserID", this@MainActivity.userId!!)
                        }
                    }
                } else {
                    null
                }

            }
            R.id.fragment_points -> {
                null

            }
            R.id.fragment_profile -> {

                null
            }
            else -> {
                null
            }
        }

        when {
            fragment != null -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.containter, fragment as Fragment, tag)
                    .commit()
            }
            itemId == R.id.fragment_bookings -> {
                showAlertLogin()
            }
            itemId == R.id.fragment_profile -> {
                if (userId != 0) {
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                } else {
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            else -> {
                showAlertDevelopment()
            }
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

    private fun showAlertLogin() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Silakan Login terlebih dahulu")
            .setMessage("Untuk login silakan menekan menu Profile...")
            .setCancelable(true)
            .setPositiveButton("OK") { dialog, id ->
                dialog.dismiss()
            }
            .create()
            .show()

    }
}