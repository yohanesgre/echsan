package com.vira.echsan

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vira.echsan.databinding.ActivityMainBinding
import com.vira.echsan.ui.fragments.HomeFragment
import com.vira.echsan.ui.fragments.bookings.BookingsFragment

class MainActivity : AppCompatActivity(){

    companion object {
        const val PARAM_NAVIGATION_ID = "navigation_id"

        fun newInstance(context: Context, navigationId: Int) =  Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra(PARAM_NAVIGATION_ID, navigationId)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding:ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
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
                HomeFragment.newInstance()
            }
            R.id.fragment_bookings -> {
                BookingsFragment.newInstance()
            }
            R.id.fragment_points -> {

            }
            R.id.fragment_profile-> {

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
        }
    }
}