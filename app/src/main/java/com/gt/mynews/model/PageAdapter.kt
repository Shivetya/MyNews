package com.gt.mynews.model

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.gt.mynews.fragments.PagerFragment

class PageAdapter(private val fragmentManager : FragmentManager) : FragmentStatePagerAdapter(fragmentManager){

    override fun getItem(position: Int): Fragment {
        return PagerFragment.newInstance(position)
    }

    override fun getCount(): Int {
        return 4
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Top Stories"
            1 -> "Most Popular"
            2 -> "Science"
            3 -> "Technology"
            else -> "Mysterious tab"
        }
    }

}
