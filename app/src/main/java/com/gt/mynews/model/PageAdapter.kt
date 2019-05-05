package com.gt.mynews.model


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.gt.mynews.fragments.*

class PageAdapter(fragmentManager : FragmentManager) : FragmentStatePagerAdapter(fragmentManager){

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> TopStoriesFragment.newInstance()
            1 -> MostPopularFragment.newInstance()
            2 -> ScienceFragment.newInstance()
            3 -> TechnologyFragment.newInstance()
            else -> TopStoriesFragment.newInstance()
        }

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
