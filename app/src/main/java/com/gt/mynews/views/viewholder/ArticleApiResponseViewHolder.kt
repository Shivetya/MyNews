package com.gt.mynews.views.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.gt.mynews.viewmodels.models.Article
import kotlinx.android.synthetic.main.recycler_views_item.view.*

class ArticleApiResponseViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    private val textViewTitle: TextView = itemView.fragment_items_text_title
    private val textViewDate: TextView = itemView.fragment_items_text_date
    private val textViewDescription: TextView = itemView.fragment_items_text_description
    private val imageViewArticle: ImageView = itemView.fragment_items_image_view

    fun updateWithResponse(articles: List<Article>, position: Int, glide: RequestManager) {
        textViewTitle.text = articles[position].categoryArticle
        textViewDate.text = articles[position].date
        textViewDescription.text = articles[position].articleTitle
        glide.load(articles[position].imageUrl).into(imageViewArticle)
    }

}