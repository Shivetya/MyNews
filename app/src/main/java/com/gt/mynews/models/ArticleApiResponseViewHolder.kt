package com.gt.mynews.models

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gt.mynews.data.ArticleApiResponse
import com.gt.mynews.viewmodels.models.Article
import kotlinx.android.synthetic.main.recycler_views_item.view.*

class ArticleApiResponseViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    private val textViewTitle: TextView = itemView.fragment_items_text_title
    private val textViewDate: TextView = itemView.fragment_items_text_date
    private val textViewDescription: TextView = itemView.fragment_items_text_description

    fun updateWithResponse(articles: List<Article>, position: Int) {
        textViewTitle.text = articles[position].categoryArticle
        textViewDate.text = articles[position].date
        textViewDescription.text = articles[position].articleTitle
    }

}