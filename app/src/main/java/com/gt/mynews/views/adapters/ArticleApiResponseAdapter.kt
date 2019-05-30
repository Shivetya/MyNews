package com.gt.mynews.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.gt.mynews.R
import com.gt.mynews.views.viewholder.ArticleApiResponseViewHolder
import com.gt.mynews.viewmodels.models.Article

class ArticleApiResponseAdapter(private val articlesResponse : List<Article>, private val glide : RequestManager)
    : RecyclerView.Adapter<ArticleApiResponseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleApiResponseViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.recycler_views_item, parent, false)

        return ArticleApiResponseViewHolder(view)
    }

    override fun getItemCount(): Int {

        return articlesResponse.size
    }

    override fun onBindViewHolder(holder: ArticleApiResponseViewHolder, position: Int) {
        holder.updateWithResponse(articlesResponse, position, this.glide)
    }


}