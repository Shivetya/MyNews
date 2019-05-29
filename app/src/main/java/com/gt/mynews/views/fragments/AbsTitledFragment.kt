package com.gt.mynews.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gt.mynews.R
import com.gt.mynews.data.ArticleApiResponse
import com.gt.mynews.viewmodels.GenericViewModel
import com.gt.mynews.viewmodels.ViewModelFactory
import com.gt.mynews.viewmodels.models.Article
import com.gt.mynews.views.adapters.ArticleApiResponseAdapter
import kotlinx.android.synthetic.main.fragment_generic_recycler.*

abstract class AbsTitledFragment : Fragment() {

    private lateinit var article: List<Article>
    private lateinit var adapter: ArticleApiResponseAdapter
    lateinit var fragmentGenericRV : RecyclerView

    protected val viewModel by lazy {
        ViewModelProviders.of(this, ViewModelFactory.INSTANCE).get(GenericViewModel::class.java)
    }


    abstract fun getKeyword() : String?
    abstract fun loadArticle()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val fragView = inflater.inflate(R.layout.fragment_generic_recycler, container, false)
        fragmentGenericRV = fragView.findViewById<View>(R.id.fragment_generic_recyclerview) as RecyclerView
        setObserve()
        configureRecyclerView()
        loadArticle()

        return fragView
    }

    private fun configureRecyclerView(){

        article = listOf(Article(null,null, null, null ))
        fragmentGenericRV.layoutManager = LinearLayoutManager(activity?.applicationContext)
        adapter = ArticleApiResponseAdapter(article)
        fragmentGenericRV.adapter = adapter

    }

    private fun updateUI(newArticles: List<Article>){
        article = newArticles
        adapter.notifyDataSetChanged()
    }

    private fun setObserve() {
        viewModel.articles
                .observe(this, Observer {
                    updateUI(it) })
    }

}