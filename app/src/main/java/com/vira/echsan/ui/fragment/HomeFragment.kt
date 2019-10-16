package com.vira.echsan.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vira.echsan.R
import com.vira.echsan.ui.activity.umroh_haji.UmrohActivity
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : androidx.fragment.app.Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        val view:View = inflater.inflate(R.layout.fragment_home, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_home_umroh_haji.setOnClickListener {
            val intent = Intent(activity, UmrohActivity::class.java)
            activity!!.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true;
    }
    companion object {
        fun newInstance() : HomeFragment = HomeFragment()
    }
}