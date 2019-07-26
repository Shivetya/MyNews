package com.gt.mynews.views.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.gt.mynews.R
import com.gt.mynews.views.adapters.PageAdapter
import net.danlew.android.joda.JodaTimeAndroid

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        JodaTimeAndroid.init(this)

        setContentView(R.layout.activity_main)

        this.configureToolbar()
        this.configurePagerAndTabs()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu_activity_main, menu)
        return true
    }

    private fun configureToolbar() {

        val toolbar = findViewById<Toolbar>(R.id.activity_toolbar)
        setSupportActionBar(toolbar)
    }

    private fun configurePagerAndTabs(){

        val viewPager = findViewById<ViewPager>(R.id.activity_main_view_pager)
        viewPager.adapter = PageAdapter(supportFragmentManager)

        val tabLayout = findViewById<TabLayout>(R.id.activity_main_tabs)

        tabLayout.setupWithViewPager(viewPager)
        tabLayout.tabMode = TabLayout.MODE_FIXED
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item!!.itemId){
            R.id.menu_activity_main_search -> launchSearchActivity()
            R.id.menu_activity_main_notifications -> launchNotificationActivity()
            R.id.menu_activity_main_help -> launchHelpActivity()
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    private fun launchSearchActivity(){
        val intent = Intent(this, SearchActivity::class.java)
        this.startActivity(intent)
    }

    private fun launchNotificationActivity(){
        val intent = Intent(this, NotificationActivity::class.java)
        this.startActivity(intent)
    }

    private fun launchHelpActivity(){
        val intent = Intent(this, HelpActivity::class.java)
        this.startActivity(intent)
    }

}
