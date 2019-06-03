package com.gt.mynews.views.fragments


import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this, ViewModelFactory.INSTANCE).get(MostPopularViewModel::class.java)
        super.onCreate(savedInstanceState)
    }

    companion object{

        fun newInstance() : MostPopularFragment = MostPopularFragment()
    }

    override fun getKeyword(): String? = getString(R.string.most_popular)

    override fun loadArticle() {
        viewModel.reloadArticles(null)
    }

}
