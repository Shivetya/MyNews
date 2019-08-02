package com.gt.mynews.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProviders
import com.gt.mynews.R
import com.gt.mynews.viewmodels.NotificationViewModel
import com.gt.mynews.viewmodels.ViewModelFactory
import kotlinx.android.synthetic.main.activity_notification.*
import kotlinx.android.synthetic.main.edit_text_search_query.*

class NotificationActivity : AppCompatActivity() {

    private lateinit var viewModel: NotificationViewModel
    private val checkboxes: ArrayList<CheckBox> = arrayListOf()
    private val checkboxesTagChecked: MutableCollection<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        viewModel = ViewModelProviders.of(this, ViewModelFactory.INSTANCE).get(NotificationViewModel::class.java)

        configureToolbar()
        addListenersToCheckBoxes()
        configureSwitchNotif()
        configureEditText()

    }

    override fun onDestroy() {

        for(checkbox in checkboxes){
            if (checkbox.isChecked){
                checkboxesTagChecked.add(checkbox.tag as String)
            }
        }
        viewModel.saveKeywordFilters(checkboxesTagChecked)
        super.onDestroy()
    }

    private fun configureToolbar(){
        val toolbar = findViewById<Toolbar>(R.id.activity_toolbar)

        setSupportActionBar(toolbar)

        val actionBar = supportActionBar

        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun addListenersToCheckBoxes(){

        val stringKeywordFilter = viewModel.getKeywordFilterSaved()

        findViewById<CheckBox>(R.id.fragment_search_checkbox_arts_1).let {
            //it.setOnClickListener(this)
            it.tag = "arts"
            it.isChecked = stringKeywordFilter.contains("arts")
            checkboxes.add(it)
        }
        findViewById<CheckBox>(R.id.fragment_search_checkbox_business_2).let {
            //it.setOnClickListener(this)
            it.tag = "business"
            it.isChecked = stringKeywordFilter.contains("business")
            checkboxes.add(it)
        }
        findViewById<CheckBox>(R.id.fragment_search_checkbox_entrepreneurs_3).let {
            //it.setOnClickListener(this)
            it.tag = "entrepreneur"
            it.isChecked = stringKeywordFilter.contains("entrepreneur")
            checkboxes.add(it)
        }
        findViewById<CheckBox>(R.id.fragment_search_checkbox_politics_4).let {
            //it.setOnClickListener(this)
            it.tag = "politics"
            it.isChecked = stringKeywordFilter.contains("politics")
            checkboxes.add(it)

        }
        findViewById<CheckBox>(R.id.fragment_search_checkbox_sports_5).let {
            //it.setOnClickListener(this)
            it.tag = "sports"
            it.isChecked = stringKeywordFilter.contains("sports")
            checkboxes.add(it)

        }
        findViewById<CheckBox>(R.id.fragment_search_checkbox_travel_6).let {
            //it.setOnClickListener(this)
            it.tag = "travel"
            it.isChecked = stringKeywordFilter.contains("travel")
            checkboxes.add(it)
        }

    }

    /*override fun onClick(v: View?) {
        if(v is CheckBox){
            val checked = v.isChecked

            if(checked){
                viewModel.addKeywordFilter(v.tag as String)
            } else {
                viewModel.removeKeywordFilter(v.tag as String)
            }
        }
    }*/

    private fun configureSwitchNotif(){
        activity_notifications_switch.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onNotificationEnabled(isChecked)
        }

        activity_notifications_switch.isChecked = viewModel.isSwitchOn()


    }

    private fun configureEditText(){
        edittext_keyword.setText(viewModel.getKeywordSaved())
        edittext_keyword.addTextChangedListener {
            val keyword = it.toString()
            viewModel.saveKeyword(keyword)
        }
    }
}
