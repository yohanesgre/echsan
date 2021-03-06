package com.vira.echsan.features.member

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.vira.echsan.R
import com.vira.echsan.adapters.member.MemberAdapter
import com.vira.echsan.databinding.ActivityMemberBinding
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MemberActivity : AppCompatActivity(), HasSupportFragmentInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    private lateinit var adapter: MemberAdapter
    private lateinit var binding: ActivityMemberBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_member)
        initUI()
    }

    private fun initUI() {
        binding.toolbar.title = "Member"
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        adapter = MemberAdapter()
        val list = listOf(
            listOf(
                "Free",
                "VIP Member",
                "1 Voucher Umroh",
                "Point",
                "Double Point",
                "Agent Reward",
                "Agent Gathering",
                "Agent Refferal"
            ),
            listOf(
                "Rp. 2.500.000",
                "Previllage Member",
                "10 Voucher Umroh",
                "Point",
                "Double Point",
                "Agent Reward",
                "Agent Gathering",
                "Agent Refferal"
            )
        )
        adapter.submitList(list)
        binding.rvMember.adapter = adapter
        binding.rvMember.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}