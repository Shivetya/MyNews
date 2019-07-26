package com.gt.mynews.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.gt.mynews.R

class HelpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)

        configureToolbar()
    }

    private fun configureToolbar(){
        val toolbar = findViewById<Toolbar>(R.id.activity_toolbar)

        setSupportActionBar(toolbar)

        val actionBar = supportActionBar

        actionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
