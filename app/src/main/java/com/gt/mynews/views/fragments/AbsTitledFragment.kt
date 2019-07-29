package com.gt.mynews.views.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.gt.mynews.R
import com.gt.mynews.viewmodels.GenericViewModel
import com.gt.mynews.viewmodels.models.Article
import com.gt.mynews.views.activities.WebviewActivity
import com.gt.mynews.views.adapters.ArticleApiResponseAdapter

abstract class AbsTitledFragment : Fragment(), ArticleApiResponseAdapter.Listeners {

    protected lateinit var article: MutableList<Article>
    lateinit var adapter: ArticleApiResponseAdapter

    lateinit var viewModel : GenericViewModel
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    abstract fun getKeyword() : String?
    abstract fun loadArticle()


    private fun setObserve() {
        viewModel.articles
                .observe(this, Observer {
                    updateUI(it)
                    swipeRefreshLayout.isRefreshing = false})
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val fragView = inflater.inflate(R.layout.fragment_generic_recycler, container, false)
        val fragmentGenericRV = fragView.findViewById<View>(R.id.fragment_generic_recyclerview) as RecyclerView
        swipeRefreshLayout = fragView.findViewById(R.id.fragment_generic_swipe_refresh_layout)
        setObserve()
        configureRecyclerView(fragmentGenericRV)
        loadArticle()
        configureSwipeRefreshLayout()

        return fragView
    }

    private fun configureSwipeRefreshLayout(){
        swipeRefreshLayout.setOnRefreshListener {
            loadArticle()
        }
    }

    private fun configureRecyclerView(recyclerView : RecyclerView){

        article = mutableListOf()
        recyclerView.layoutManager = LinearLayoutManager(activity?.applicationContext)
        adapter = ArticleApiResponseAdapter(article, Glide.with(context), this)
        recyclerView.adapter = adapter

    }

    private fun updateUI(newArticles: List<Article>){
        article.clear()
        article.addAll(newArticles)
        adapter.notifyDataSetChanged()
    }

    override fun onClickLaunchWebview(urlToLaunch: String) {
        val intent = Intent(context, WebviewActivity::class.java)
        intent.putExtra(WebviewActivity.KEY_URL, urlToLaunch)
        context!!.startActivity(intent)
    }

}