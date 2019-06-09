package com.gt.mynews.views.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.gt.mynews.viewmodels.TopStoriesViewModel
import com.gt.mynews.viewmodels.ViewModelFactory
import com.gt.mynews.viewmodels.models.Article

/**
 * A simple [Fragment] subclass.
 *
 */
class TopStoriesFragment : AbsTitledFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this, ViewModelFactory.INSTANCE).get(TopStoriesViewModel::class.java)
        super.onCreate(savedInstanceState)
    }

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
        viewModel.reloadArticles(arguments?.getString(KEY_KEYWORD),null, null, null)
    }
}
