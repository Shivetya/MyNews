package com.gt.mynews.views.activities

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.gt.mynews.R
import com.gt.mynews.views.adapters.PageAdapter
import net.danlew.android.joda.JodaTimeAndroid

class MainActivity : AppCompatActivity() {

    companion object{
        private const val CHANNEL_NAME = "notification.newArticles"
        private const val CHANNEL_TEXT = "There is at least one new article you want to see !"
        private const val CHANNEL_ID = "NOTIF_NEW_ARTICLE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        JodaTimeAndroid.init(this)
        createNotificationChannel()

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
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    private fun launchSearchActivity(){
        val intent = Intent(this, SearchActivity::class.java)
        this.startActivity(intent)
    }

    private fun createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = CHANNEL_NAME
            val descriptionText = CHANNEL_TEXT
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                    ContextCompat.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}
