package com.gt.mynews.views.fragments


import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.gt.mynews.R
import com.gt.mynews.viewmodels.MostPopularViewModel
import com.gt.mynews.viewmodels.ViewModelFactory
import com.gt.mynews.viewmodels.models.Article

/**
 * A simple [Fragment] subclass.
 *
 */
class MostPopularFragment : AbsTitledFragment() {

    private val viewModel by lazy {
        ViewModelProviders.of(this, ViewModelFactory.INSTANCE).get(MostPopularViewModel::class.java)
    }

    companion object{

        fun newInstance() : MostPopularFragment = MostPopularFragment()
    }

    override fun getKeyword(): String? = getString(R.string.most_popular)

    override fun loadArticle() {
        viewModel.reloadArticles(null)
    }

    override fun setObserve() {
        viewModel.articles
                .observe(this, Observer {
                    updateUI(it) })
    }

}
