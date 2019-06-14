package com.gt.mynews.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.gt.mynews.R
import com.gt.mynews.views.fragments.SearchItemsFragment
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        launchSearchFragment()

        this.configureToolbar()
    }

    private fun configureToolbar(){
        val toolbar = activity_search_toolbar as Toolbar

        setSupportActionBar(toolbar)

        val actionBar = supportActionBar

        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun launchSearchFragment(){

        val searchFragment = supportFragmentManager.findFragmentById(R.id.activity_search_framelayout)

        if(searchFragment == null){
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.activity_search_framelayout, SearchItemsFragment.newInstance())
                    .commit()
        }
    }
}
