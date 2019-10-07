package com.vira.echsan.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import com.vira.echsan.R
import com.vira.echsan.ui.fragment.HomeFragment

class MainActivity : AppCompatActivity(){
    lateinit var  toolbar: ActionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_main)
        toolbar = supportActionBar!!
        val bottomNavigation: BottomNavigationView = findViewById(R.id.navbar_bottom)
        bottomNavigation.setOnNavigationItemSelectedListener(navbarItemSelectedListener)
        val homeFragment = HomeFragment.newInstance()
        openFragment(homeFragment)
    }

    @SuppressLint("ResourceType")
    private val navbarItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId){
            R.id.nav_home -> {
                toolbar.title = getString(R.id.nav_home)
                val homeFragment = HomeFragment.newInstance()
                openFragment(homeFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_bookings -> {
                toolbar.title = getString(R.id.nav_bookings)
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_points ->{
                toolbar.title = getString(R.id.nav_points)
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_profile ->{
                toolbar.title = getString(R.id.nav_profile)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun openFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container_fragment, fragment)
            .addToBackStack(null)
            .commit()
    }
}