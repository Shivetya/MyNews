package com.gt.mynews.views.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.gt.mynews.R
import com.gt.mynews.viewmodels.models.Article
import com.gt.mynews.views.activities.WebviewActivity
import kotlinx.android.synthetic.main.recycler_views_item.view.*

class ArticleApiResponseAdapter(private val articlesResponse : List<Article>, private val glide : RequestManager)
    : RecyclerView.Adapter<ArticleApiResponseAdapter.ArticleApiResponseViewHolder>() {

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

    class ArticleApiResponseViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

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
            glide.load(articles[position].imageUrl).into(imageViewArticle)
            url = articles[position].url!!
        }

        override fun onClick(v: View?) {

            val intent = Intent(v!!.context, WebviewActivity::class.java )
            intent.putExtra(WebviewActivity.KEY_URL, url)

            v!!.context.startActivity(intent)
        }

    }

}