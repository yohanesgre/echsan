package com.vira.echsan.ui.fragment

import ShadowTransformer
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.viewpager.widget.ViewPager
import com.vira.echsan.R
import com.vira.echsan.ui.activity.umroh_haji.UmrohActivity
import com.vira.echsan.ui.carousel.CardFragmentPagerAdapter
import com.vira.echsan.ui.carousel.CardItem
import com.vira.echsan.ui.carousel.CardPagerAdapter
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : androidx.fragment.app.Fragment(), View.OnClickListener, CompoundButton.OnCheckedChangeListener{

    lateinit var mViewPager:ViewPager
    lateinit var mCardAdapter: CardPagerAdapter
    lateinit var mCardShadowTransformer: ShadowTransformer
    lateinit var mFragmentCardAdapter: CardFragmentPagerAdapter
    lateinit var mFragmentCardShadowTransformer: ShadowTransformer

    var mShowingFragments = false


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        val view:View = inflater.inflate(R.layout.fragment_home, container, false)
        mViewPager = view.findViewById(R.id.viewPager)
        mCardAdapter = CardPagerAdapter()
        mCardAdapter.addCardItem(CardItem(R.string.title_1, R.string.text_1))
        mCardAdapter.addCardItem(CardItem(R.string.title_2, R.string.text_1))
        mCardAdapter.addCardItem(CardItem(R.string.title_3, R.string.text_1))
        mCardAdapter.addCardItem(CardItem(R.string.title_4, R.string.text_1))
        mFragmentCardAdapter = CardFragmentPagerAdapter(
            childFragmentManager,
            dpToPixels(2, activity!!)
        )

        mCardShadowTransformer = ShadowTransformer(mViewPager, mCardAdapter)
        mFragmentCardShadowTransformer = ShadowTransformer(mViewPager, mFragmentCardAdapter)


        mViewPager.adapter = mCardAdapter
        mViewPager.setPageTransformer(false, mCardShadowTransformer)
        mViewPager.offscreenPageLimit = 3
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

    fun dpToPixels(dp: Int, context: Context): Float {
        return dp * context.getResources().getDisplayMetrics().density
    }

    override fun onClick(view: View) {
        if (!mShowingFragments) {
            mViewPager.adapter = mFragmentCardAdapter
            mViewPager.setPageTransformer(false, mFragmentCardShadowTransformer)
        } else {
            mViewPager.adapter = mCardAdapter
            mViewPager.setPageTransformer(false, mCardShadowTransformer)
        }

        mShowingFragments = !mShowingFragments
    }

    override fun onCheckedChanged(compoundButton: CompoundButton, b: Boolean) {
        mCardShadowTransformer.enableScaling(b)
        mFragmentCardShadowTransformer.enableScaling(b)
    }

    companion object {
        fun newInstance() : HomeFragment = HomeFragment()
    }
}