package com.gt.mynews.views.fragments


import androidx.fragment.app.Fragment

import com.gt.mynews.R

/**
 * A simple [Fragment] subclass.
 *
 */
class TopStoriesFragment : AbsTitledFragment() {

    companion object{
        fun newInstance() : TopStoriesFragment = TopStoriesFragment()
    }

    override fun getKeyword(): String? = getString(R.string.top_stories)

}
