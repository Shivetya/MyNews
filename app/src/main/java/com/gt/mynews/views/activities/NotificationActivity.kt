package com.gt.mynews.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import com.gt.mynews.R
import com.gt.mynews.viewmodels.NotificationViewModel
import com.gt.mynews.viewmodels.ViewModelFactory
import kotlinx.android.synthetic.main.activity_notification.*

class NotificationActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var viewModel: NotificationViewModel
    private val mKeywordFilter : ArrayList<String> = ArrayList()
    private var mKeyword: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        viewModel = ViewModelProviders.of(this, ViewModelFactory.INSTANCE).get(NotificationViewModel::class.java)

        configureToolbar()
        addListenersToCheckBoxes()
        configureSwitchNotif()

    }

    override fun onDestroy() {
        viewModel.saveKeyword(mKeyword)
        viewModel.saveKeywordFilter(mKeywordFilter)
        super.onDestroy()
    }

    private fun configureToolbar(){
        val toolbar = findViewById<Toolbar>(R.id.activity_toolbar)

        setSupportActionBar(toolbar)

        val actionBar = supportActionBar

        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun addListenersToCheckBoxes(){

        findViewById<CheckBox>(R.id.fragment_search_checkbox_arts_1).let {
            it.setOnClickListener(this)
            it.tag = "arts"
        }
        findViewById<CheckBox>(R.id.fragment_search_checkbox_business_2).let {
            it.setOnClickListener(this)
            it.tag = "business"
        }
        findViewById<CheckBox>(R.id.fragment_search_checkbox_entrepreneurs_3).let {
            it.setOnClickListener(this)
            it.tag = "entrepreneur"
        }
        findViewById<CheckBox>(R.id.fragment_search_checkbox_politics_4).let {
            it.setOnClickListener(this)
            it.tag = "politics"
        }
        findViewById<CheckBox>(R.id.fragment_search_checkbox_sports_5).let {
            it.setOnClickListener(this)
            it.tag = "sports"
        }
        findViewById<CheckBox>(R.id.fragment_search_checkbox_travel_6).let {
            it.setOnClickListener(this)
            it.tag = "travel"
        }

    }

    override fun onClick(v: View?) {
        if(v is CheckBox){
            val checked = v.isChecked

            if(checked){
                mKeywordFilter.add(v.tag as String)
            } else {
                mKeywordFilter.remove(v.tag as String)
            }
        }
    }

    private fun configureSwitchNotif(){
        activity_notifications_switch.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onSwitchTouched(isChecked)
        }

        activity_notifications_switch.isChecked = viewModel.isSwitchOn()

    }

}
