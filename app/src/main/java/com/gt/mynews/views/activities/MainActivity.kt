package com.gt.mynews.views.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.viewpager.widget.ViewPager
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.gt.mynews.R
import com.gt.mynews.views.fragments.MostPopularFragment
import com.gt.mynews.views.fragments.PagerFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import net.danlew.android.joda.JodaTimeAndroid

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener
        ,PagerFragment.SetupViewPagerInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        JodaTimeAndroid.init(this)

        setContentView(R.layout.activity_main)

        this.configureToolbar()
        this.configureDrawerLayout()
        this.configureNavigationView()

        launchMostPopularFragment()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu_activity_main, menu)
        return true
    }

    private fun configureToolbar() {
        setSupportActionBar(activity_toolbar)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.menu_activity_main_search -> launchSearchActivity()
            R.id.menu_activity_main_notifications -> launchNotificationActivity()
            R.id.menu_activity_main_help -> launchHelpActivity()
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.activity_main_drawer_most_popular -> launchMostPopularFragment()
            R.id.activity_main_drawer_top_stories -> launchTopStoriesPager()
            R.id.activity_main_drawer_search -> launchSearchActivity()
            R.id.activity_main_drawer_notifications -> launchNotificationActivity()
            R.id.activity_main_drawer_help -> launchHelpActivity()
            R.id.activity_main_drawer_about -> return true
        }

        activity_main_drawer_layout.closeDrawer(GravityCompat.START)

        return true
    }

    override fun onBackPressed() {
        if (activity_main_drawer_layout.isDrawerOpen(GravityCompat.START)){
            activity_main_drawer_layout.closeDrawer(GravityCompat.START)
        }else {
            super.onBackPressed()
        }

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

    private fun configureDrawerLayout(){

        val toggle = ActionBarDrawerToggle(this,activity_main_drawer_layout, activity_toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)

        activity_main_drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun configureNavigationView(){

        activity_main_navigation_view.setNavigationItemSelectedListener(this)
    }

    private fun launchMostPopularFragment(){

        supportFragmentManager.beginTransaction()
                .replace(R.id.activity_main_framelayout, MostPopularFragment.newInstance())
                .commit()

        activity_main_navigation_view.setCheckedItem(R.id.activity_main_drawer_most_popular)

        activity_main_tabs.removeAllTabs()
        activity_main_tabs.addTab(activity_main_tabs.newTab().setText(R.string.most_popular))
    }

    private fun launchTopStoriesPager(){

        supportFragmentManager.beginTransaction()
                .replace(R.id.activity_main_framelayout, PagerFragment.newInstance())
                .commit()
    }

    override fun setupViewPager(viewPager: ViewPager) {
        activity_main_tabs.setupWithViewPager(viewPager)
        activity_main_tabs.tabMode = TabLayout.MODE_FIXED
    }
}
