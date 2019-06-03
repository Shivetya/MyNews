package com.gt.mynews.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gt.mynews.R
import com.gt.mynews.viewmodels.GenericViewModel
import com.gt.mynews.viewmodels.TopStoriesViewModel
import com.gt.mynews.viewmodels.ViewModelFactory
import com.gt.mynews.viewmodels.models.Article
import com.gt.mynews.views.adapters.ArticleApiResponseAdapter

abstract class AbsTitledFragment : Fragment() {

    protected lateinit var article: MutableList<Article>
    lateinit var adapter: ArticleApiResponseAdapter

    lateinit var viewModel : GenericViewModel

    abstract fun getKeyword() : String?
    abstract fun loadArticle()


    private fun setObserve() {
        viewModel.articles
                .observe(this, Observer {
                    updateUI(it) })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val fragView = inflater.inflate(R.layout.fragment_generic_recycler, container, false)
        val fragmentGenericRV = fragView.findViewById<View>(R.id.fragment_generic_recyclerview) as RecyclerView
        setObserve()
        configureRecyclerView(fragmentGenericRV)
        loadArticle()

        return fragView
    }

    private fun configureRecyclerView(recyclerView : RecyclerView){

        article = mutableListOf()
        recyclerView.layoutManager = LinearLayoutManager(activity?.applicationContext)
        adapter = ArticleApiResponseAdapter(article, Glide.with(context))
        recyclerView.adapter = adapter

    }

    private fun updateUI(newArticles: List<Article>){
        article.clear()
        article.addAll(newArticles)
        adapter.notifyDataSetChanged()
    }



}