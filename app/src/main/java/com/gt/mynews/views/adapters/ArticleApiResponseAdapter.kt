package com.gt.mynews.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.gt.mynews.R
import com.gt.mynews.viewmodels.models.Article
import kotlinx.android.synthetic.main.recycler_views_item.view.*

class ArticleApiResponseAdapter(private val articlesResponse : List<Article>,
                                private val glide : RequestManager,
                                private val callback: Listeners)
    : RecyclerView.Adapter<ArticleApiResponseAdapter.ArticleApiResponseViewHolder>() {

    interface Listeners{
        fun onClickLaunchWebview(urlToLaunch: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleApiResponseViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.recycler_views_item, parent, false)

        return ArticleApiResponseViewHolder(view, callback)
    }

    override fun getItemCount(): Int {

        return articlesResponse.size
    }

    override fun onBindViewHolder(holder: ArticleApiResponseViewHolder, position: Int) {
        holder.updateWithResponse(articlesResponse, position, this.glide)
    }

    class ArticleApiResponseViewHolder(itemView : View,
                                       private val callback: Listeners)
        : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private val textViewTitle: TextView = itemView.fragment_items_text_title
        private val textViewDate: TextView = itemView.fragment_items_text_date
        private val textViewDescription: TextView = itemView.fragment_items_text_description
        private val imageViewArticle: ImageView = itemView.fragment_items_image_view
        private lateinit var url : String

        init {
            itemView.setOnClickListener(this)
        }

        fun updateWithResponse(articles: List<Article>, position: Int, glide: RequestManager) {
            textViewTitle.text = articles[position].categoryArticle
            textViewDate.text = articles[position].date
            textViewDescription.text = articles[position].articleTitle
            url = articles[position].url!!

            if (articles[position].imageUrl?.contains("http") == true){
                glide.load(articles[position].imageUrl?.replace("https", "http")).into(imageViewArticle)
            } else{
                glide.load(R.drawable.newyorktime_logo).into(imageViewArticle)
            }
        }

        override fun onClick(v: View?) {

            callback.onClickLaunchWebview(url)
        }

    }

}