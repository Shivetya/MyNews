package com.gt.mynews.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.view.Menu

import com.gt.mynews.R
import com.gt.mynews.model.PageAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.configureToolbar()
        this.configurePager();
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu_activity_main, menu)
        return true
    }

    private fun configureToolbar() {

        val toolbar = findViewById<Toolbar>(R.id.activity_main_toolbar)
        setSupportActionBar(toolbar)
    }

    private fun configurePager(){

        val viewPager = findViewById<ViewPager>(R.id.activity_main_view_pager)

        viewPager.adapter = PageAdapter(supportFragmentManager)
    }
}
