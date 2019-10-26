package com.vira.echsan.ui.activity

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.vira.echsan.R
import com.vira.echsan.ui.fragment.bookings.BookingsFragment
import com.vira.echsan.ui.fragment.HomeFragment

class MainActivity : AppCompatActivity(){
    lateinit var toolbar : Toolbar
    lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        bottomNavigation = findViewById(R.id.navbar_bottom)
        bottomNavigation.setOnNavigationItemSelectedListener(navbarItemSelectedListener)
        toolbar?.title = getString(R.string.nav_home)
        val homeFragment = HomeFragment.newInstance()
        openFragment(homeFragment)
    }

    private val navbarItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId){
            R.id.nav_home -> {
                toolbar?.title = getString(R.string.nav_home)
                val homeFragment = HomeFragment.newInstance()
                openFragment(homeFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_bookings -> {
                toolbar?.title = getString(R.string.nav_bookings)
                val bookingsFragment = BookingsFragment.newInstance()
                openFragment(bookingsFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_points ->{
                toolbar.title = getString(R.string.nav_points)
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_profile ->{
                toolbar.title = getString(R.string.nav_profile)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun openFragment(fragment: androidx.fragment.app.Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container_fragment, fragment)
            .commit()
    }
}