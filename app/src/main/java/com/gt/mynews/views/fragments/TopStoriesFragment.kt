package com.gt.mynews.views.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment

/**
 * A simple [Fragment] subclass.
 *
 */
class TopStoriesFragment : AbsTitledFragment() {

    companion object{

        private const val KEY_KEYWORD = "KEY_KEYWORD"

        fun newInstance(keyWord : String) : TopStoriesFragment {

            val frag = TopStoriesFragment()

            val args = Bundle(1)
            args.putString(KEY_KEYWORD, keyWord)

            frag.arguments = args

            return frag
        }
    }

    override fun getKeyword(): String? = arguments?.getString(KEY_KEYWORD)

    override fun loadArticle() {
        super.viewModel.reloadArticlesTS(arguments?.getString(KEY_KEYWORD))
    }

}
