package com.gt.mynews.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import androidx.appcompat.widget.Toolbar
import com.gt.mynews.R
import com.gt.mynews.data.repositories.NotificationSettingsArticlesSaved
import com.gt.mynews.usecases.NotificationUseCase
import com.gt.mynews.viewmodels.NotificationViewModel

class NotificationActivity : AppCompatActivity(), View.OnClickListener {

    private val viewModel = NotificationViewModel(NotificationUseCase(NotificationSettingsArticlesSaved(this)))
    private val mKeywordFilter : ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        configureToolbar()
    }

    private fun configureToolbar(){
        val toolbar = findViewById<Toolbar>(R.id.activity_toolbar)

        setSupportActionBar(toolbar)

        val actionBar = supportActionBar

        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onClick(v: View?) {
        if(v is CheckBox){
            val checked = v.isChecked
            when (v.id){
                R.id.fragment_search_checkbox_arts_1 -> {
                    if(checked) mKeywordFilter.add("\"arts\"")
                    else mKeywordFilter.remove("\"arts\"")
                }
                R.id.fragment_search_checkbox_business_2 ->{
                    if(checked) mKeywordFilter.add("\"business\"")
                    else mKeywordFilter.remove("\"business\"")
                }
                R.id.fragment_search_checkbox_entrepreneurs_3 ->{
                    if(checked) mKeywordFilter.add("\"entrepreneurs\"")
                    else mKeywordFilter.remove("\"entrepreneurs\"")
                }
                R.id.fragment_search_checkbox_politics_4 ->{
                    if(checked) mKeywordFilter.add("\"politics\"")
                    else mKeywordFilter.remove("\"politics\"")
                }
                R.id.fragment_search_checkbox_sports_5 ->{
                    if(checked) mKeywordFilter.add("\"sports\"")
                    else mKeywordFilter.remove("\"sports\"")
                }
                R.id.fragment_search_checkbox_travel_6 ->{
                    if(checked) mKeywordFilter.add("\"travel\"")
                    else mKeywordFilter.remove("\"travel\"")
                }
            }
        }
    }


}
