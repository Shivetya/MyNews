package com.gt.mynews.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gt.mynews.R
import com.gt.mynews.data.ArticleApiResponse
import com.gt.mynews.views.adapters.ArticleApiResponseAdapter
import kotlinx.android.synthetic.main.fragment_generic_recycler.*

abstract class AbsTitledFragment : Fragment() {

    private lateinit var articleApiResponse: ArticleApiResponse
    private lateinit var adapter: ArticleApiResponseAdapter
    private val recyclerView : RecyclerView = fragment_generic_recyclerview

    abstract fun getKeyword() : String?

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        configureRecyclerView()

        return inflater.inflate(R.layout.fragment_generic_recycler, container, false)
    }

    private fun configureRecyclerView(){

        articleApiResponse = ArticleApiResponse()
        adapter = ArticleApiResponseAdapter(articleApiResponse)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)

    }


}