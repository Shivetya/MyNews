package com.gt.mynews.views.fragments


import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.button.MaterialButton
import com.gt.mynews.R
import com.gt.mynews.viewmodels.GenericViewModel
import com.gt.mynews.viewmodels.SearchViewModel
import com.gt.mynews.viewmodels.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_search_items.*
import java.util.*
import kotlin.collections.ArrayList


class SearchItemsFragment : Fragment(), View.OnClickListener {

    interface ListenerSearch{
        fun launchSearch(keyword: String?, keywordFilter: ArrayList<String>?, beginDate: String?, endDate: String?)
    }

    private lateinit var viewModel : GenericViewModel

    private var keyword : String? = null
    private var beginDate : String? = null
    private var endDate : String? = null
    private val keywordFilter : ArrayList<String> = ArrayList()
    private lateinit var listener: ListenerSearch

    companion object {
        fun newInstance() : SearchItemsFragment {
            return SearchItemsFragment()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        listener = activity as ListenerSearch
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProviders.of(this, ViewModelFactory.INSTANCE).get(SearchViewModel::class.java)

        return inflater.inflate(R.layout.fragment_search_items, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addListenerToTextInputEditTextKeyword()
        addListenerToTextInputEditTextBeginDate()
        addListenerToTextInputEditTextEndDate()
        addListenersToCheckBoxes(view)

        view.findViewById<MaterialButton>(R.id.fragment_search_button_search).setOnClickListener {
            launchSearch(keyword, keywordFilter, beginDate, endDate)
        }

    }

    private fun addListenerToTextInputEditTextKeyword(){

        view!!.findViewById<EditText>(R.id.edittext_keyword).addTextChangedListener{
            keyword = "$it"
        }
    }

    private fun addListenerToTextInputEditTextBeginDate() {

        view!!.findViewById<EditText>(R.id.fragment_search_edittext_begin_date).setOnClickListener {
            showDatePickerDialog(it)
        }
    }

    private fun addListenerToTextInputEditTextEndDate() {

        view!!.findViewById<EditText>(R.id.fragment_search_edittext_end_date).setOnClickListener {
            showDatePickerDialog(it)
        }
    }

    private fun showDatePickerDialog(textInputEditText : View){
        val actualYear = Calendar.getInstance().get(Calendar.YEAR)
        val actualMonth = Calendar.getInstance().get(Calendar.MONTH)
        val actualDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(context!!, DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            when (textInputEditText.id){
                R.id.fragment_search_edittext_begin_date -> {
                    beginDate = "$dayOfMonth-${monthOfYear+1}-$year"
                    fragment_search_edittext_begin_date.setText(beginDate, TextView.BufferType.EDITABLE)
                }
                R.id.fragment_search_edittext_end_date -> {
                    endDate = "$dayOfMonth-${monthOfYear+1}-$year"
                    fragment_search_edittext_end_date.setText(endDate, TextView.BufferType.EDITABLE)
                }
            }
        }, actualYear, actualMonth, actualDay)

        datePickerDialog.show()
    }

    private fun addListenersToCheckBoxes(view: View){

        view.findViewById<CheckBox>(R.id.fragment_search_checkbox_arts_1).let {
            it.setOnClickListener(this)
            it.tag = "arts"
        }
        view.findViewById<CheckBox>(R.id.fragment_search_checkbox_business_2).let {
            it.setOnClickListener(this)
            it.tag = "business"
        }
        view.findViewById<CheckBox>(R.id.fragment_search_checkbox_entrepreneurs_3).let {
            it.setOnClickListener(this)
            it.tag = "entrepreneur"
        }
        view.findViewById<CheckBox>(R.id.fragment_search_checkbox_politics_4).let {
            it.setOnClickListener(this)
            it.tag = "politics"
        }
        view.findViewById<CheckBox>(R.id.fragment_search_checkbox_sports_5).let {
            it.setOnClickListener(this)
            it.tag = "sports"
        }
        view.findViewById<CheckBox>(R.id.fragment_search_checkbox_travel_6).let {
            it.setOnClickListener(this)
            it.tag = "travel"
        }

    }

    override fun onClick(v: View?) {
        if(v is CheckBox){
            val checked = v.isChecked

            if(checked){
                keywordFilter.add(v.tag as String)
            } else {
                keywordFilter.remove(v.tag as String)
            }
        }
    }

    private fun launchSearch(keyword: String?, keywordFilter: ArrayList<String>?, beginDate: String?, endDate: String?){

        listener.launchSearch(keyword, keywordFilter, beginDate, endDate)
    }

}
