package com.gt.mynews.views.adapters



import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.gt.mynews.views.fragments.*

class PageAdapter(fragmentManager : FragmentManager) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    private val fragments = listOf(
            TopStoriesFragment.newInstance("home"),
            MostPopularFragment.newInstance(),
            TopStoriesFragment.newInstance("science"),
            TopStoriesFragment.newInstance("technology")
    )

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = fragments.size

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
