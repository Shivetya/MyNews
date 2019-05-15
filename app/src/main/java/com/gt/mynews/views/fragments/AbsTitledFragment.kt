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
    private val recyclerView : RecyclerView = fragment_generic_recyclerview

    private val viewModel by lazy {
        ViewModelProviders.of(this, ViewModelFactory.INSTANCE).get(GenericViewModel::class.java)
    }

    abstract fun getKeyword() : String?

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        configureRecyclerView()

        viewModel.articles
                .observe(this, Observer { updateUI(it) })

        return inflater.inflate(R.layout.fragment_generic_recycler, container, false)
    }

    private fun configureRecyclerView(){

        article = listOf(Article(null,null, null, null ))
        adapter = ArticleApiResponseAdapter(article)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)

    }

    private fun updateUI(newArticles: List<Article>){
        article = newArticles
        adapter.notifyDataSetChanged()
    }
}