package com.gt.mynews.Model

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.gt.mynews.Fragments.PagerFragment

class PagerAdapter(fragmentManager : FragmentManager) : FragmentStatePagerAdapter(fragmentManager){

    override fun getItem(position: Int): Fragment {
        return PagerFragment.newInstance(position)
    }

    override fun getCount(): Int {
        return 4
    }

}
