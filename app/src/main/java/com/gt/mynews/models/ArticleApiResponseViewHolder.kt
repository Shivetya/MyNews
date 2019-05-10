package com.gt.mynews.models

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gt.mynews.data.ArticleApiResponse
import kotlinx.android.synthetic.main.recycler_views_item.view.*

class ArticleApiResponseViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    private val textViewTitle : TextView = itemView.fragment_items_text_title
    private val textViewDate : TextView = itemView.fragment_items_text_date
    private val textViewDescription : TextView = itemView.fragment_items_text_description

    fun updateWithApiResponseS(articleApiResponse : ArticleApiResponse, position : Int){
        textViewTitle.text = articleApiResponse.response?.docs?.get(position)?.keywords?.get(0)?.value
        textViewDate.text = articleApiResponse.response?.docs?.get(position)?.pubDate
        textViewDescription.text = articleApiResponse.response?.docs?.get(position)?.snippet
    }

    fun updateWithApiResponseMPTS(articleApiResponse: ArticleApiResponse, position: Int){
        val stringTitle : String = ("${articleApiResponse.results?.get(position)?.section} > ${articleApiResponse.results?.get(position)?.geoFacet}")
        textViewTitle.text = stringTitle
        textViewDate.text = articleApiResponse.results?.get(position)?.publishedDate
        textViewDescription.text = articleApiResponse.results?.get(position)?.title
    }
}