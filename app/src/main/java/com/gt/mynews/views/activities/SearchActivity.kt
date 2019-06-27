package com.gt.mynews.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.gt.mynews.R
import com.gt.mynews.views.fragments.GenericSearchFragment
import com.gt.mynews.views.fragments.SearchItemsFragment

class SearchActivity : AppCompatActivity(), SearchItemsFragment.ListenerSearch {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        launchSearchItemFragment()

        this.configureToolbar()
    }

    private fun configureToolbar(){
        val toolbar = findViewById<Toolbar>(R.id.activity_toolbar)

        setSupportActionBar(toolbar)

        val actionBar = supportActionBar

        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun launchSearchItemFragment(){

        val searchFragment = supportFragmentManager.findFragmentById(R.id.activity_search_framelayout)

        if(searchFragment == null){
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.activity_search_framelayout, SearchItemsFragment.newInstance())
                    .commit()
        }
    }

    override fun launchSearch(keyword: String?, keywordFilter: ArrayList<String>?, beginDate: String?, endDate: String?) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.activity_search_framelayout, GenericSearchFragment.newInstance(keyword, keywordFilter, beginDate, endDate))
                .commit()
    }
}
