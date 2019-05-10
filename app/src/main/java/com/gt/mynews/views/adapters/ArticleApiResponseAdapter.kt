package com.gt.mynews.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gt.mynews.R
import com.gt.mynews.data.ArticleApiResponse
import com.gt.mynews.models.ArticleApiResponseViewHolder

class ArticleApiResponseAdapter(private val articleResponse : ArticleApiResponse) : RecyclerView.Adapter<ArticleApiResponseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleApiResponseViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.recycler_views_item, parent, false)

        return ArticleApiResponseViewHolder(view)
    }

    override fun getItemCount(): Int {

        val articleResponseCountable = articleResponse

        return if(articleResponseCountable.response?.docs?.size != null) {
            articleResponseCountable!!.response!!.docs!!.size
        }
        else articleResponseCountable!!.results!!.size
    }

    override fun onBindViewHolder(holder: ArticleApiResponseViewHolder, position: Int) {
        if(articleResponse.response?.docs?.size != null) holder.updateWithApiResponseS(articleResponse, position)
        else holder.updateWithApiResponseMPTS(articleResponse,position)
    }


}