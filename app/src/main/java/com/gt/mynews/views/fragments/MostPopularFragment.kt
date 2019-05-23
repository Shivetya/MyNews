package com.gt.mynews.views.fragments


import androidx.fragment.app.Fragment

import com.gt.mynews.R

/**
 * A simple [Fragment] subclass.
 *
 */
class MostPopularFragment : AbsTitledFragment() {


    companion object{

        fun newInstance() : MostPopularFragment = MostPopularFragment()
    }

    override fun getKeyword(): String? = getString(R.string.most_popular)

    override fun loadArticle() {
        super.viewModel.reloadArticlesMP()
    }
}
