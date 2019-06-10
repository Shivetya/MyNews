package com.gt.mynews.views.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.gt.mynews.viewmodels.SearchViewModel
import com.gt.mynews.viewmodels.ViewModelFactory

/**
 * A simple [Fragment] subclass.
 *
 */
class GenericSearchFragment : AbsTitledFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this, ViewModelFactory.INSTANCE).get(SearchViewModel::class.java)
        super.onCreate(savedInstanceState)
    }

    companion object{

        private const val KEY_KEYWORD = "KEY_KEYWORD"
        private const val KEYWORD_FILTER = "KEYWORD_FILTER"
        private const val BEGIN_DATE = "BEGIN_DATE"
        private const val END_DATE = "END_DATE"

        fun newInstance(keyWord : String?, keywordFilter: String?, beginDate: String?, endDate: String?) : GenericSearchFragment {

            val frag = GenericSearchFragment()

            val args = Bundle(4)
            args.putString(KEY_KEYWORD, keyWord)
            args.putString(KEYWORD_FILTER, keywordFilter)
            args.putString(BEGIN_DATE, beginDate)
            args.putString(END_DATE, endDate)

            frag.arguments = args

            return frag
        }
    }


    override fun getKeyword() :String? = arguments?.getString(KEY_KEYWORD)

    override fun loadArticle() {
        viewModel.reloadArticles(arguments?.getString(KEY_KEYWORD),
                arguments?.getString(KEYWORD_FILTER),
                arguments?.getString(BEGIN_DATE),
                arguments?.getString(END_DATE))
    }

}
