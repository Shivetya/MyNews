package com.gt.mynews.views.fragments


import android.app.DatePickerDialog
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


class SearchItemsFragment : Fragment(), View.OnClickListener {

    interface ListenerSearch{
        fun launchSearch(keyword: String?, keywordFilter: String?, beginDate: String?, endDate: String?)
    }

    private lateinit var viewModel : GenericViewModel

    private var mKeyword : String? = null
    private var mBeginDate : String? = null
    private var mEndDate : String? = null
    private var mKeywordFilter : MutableList<String>? = mutableListOf()

    companion object {
        fun newInstance() : SearchItemsFragment {
            return SearchItemsFragment()
        }
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
        addListenersToCheckBoxes()

        view.findViewById<MaterialButton>(R.id.fragment_search_button_search).setOnClickListener {
            val keywordF = if(mKeywordFilter!!.isEmpty()){
                null
            } else{
                mKeywordFilter!!.joinToString(" ")
            }
            launchSearch(mKeyword, keywordF, mEndDate, mBeginDate)
        }

    }

    private fun addListenerToTextInputEditTextKeyword(){

        view!!.findViewById<EditText>(R.id.edittext_keyword).addTextChangedListener{
            mKeyword = "$it"
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
                    mBeginDate = "$dayOfMonth-${monthOfYear+1}-$year"
                    fragment_search_edittext_begin_date.setText(mBeginDate, TextView.BufferType.EDITABLE)
                }
                R.id.fragment_search_edittext_end_date -> {
                    mEndDate = "$dayOfMonth-${monthOfYear+1}-$year"
                    fragment_search_edittext_end_date.setText(mEndDate, TextView.BufferType.EDITABLE)
                }
            }
        }, actualYear, actualMonth, actualDay)

        datePickerDialog.show()
    }

    private fun addListenersToCheckBoxes(){

        view!!.findViewById<CheckBox>(R.id.fragment_search_checkbox_arts_1).setOnClickListener(this)
        view!!.findViewById<CheckBox>(R.id.fragment_search_checkbox_business_2).setOnClickListener(this)
        view!!.findViewById<CheckBox>(R.id.fragment_search_checkbox_entrepreneurs_3).setOnClickListener(this)
        view!!.findViewById<CheckBox>(R.id.fragment_search_checkbox_politics_4).setOnClickListener(this)
        view!!.findViewById<CheckBox>(R.id.fragment_search_checkbox_sports_5).setOnClickListener(this)
        view!!.findViewById<CheckBox>(R.id.fragment_search_checkbox_travel_6).setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        if(v is CheckBox){
            val checked = v.isChecked

            when (v.id){
                R.id.fragment_search_checkbox_arts_1 -> {
                    if(checked) mKeywordFilter?.add("\"arts\"")
                    else mKeywordFilter?.remove("\"arts\"")
                }
                R.id.fragment_search_checkbox_business_2 ->{
                    if(checked) mKeywordFilter?.add("\"business\"")
                    else mKeywordFilter?.remove("\"business\"")
                }
                R.id.fragment_search_checkbox_entrepreneurs_3 ->{
                    if(checked) mKeywordFilter?.add("\"entrepreneurs\"")
                    else mKeywordFilter?.remove("\"entrepreneurs\"")
                }
                R.id.fragment_search_checkbox_politics_4 ->{
                    if(checked) mKeywordFilter?.add("\"politics\"")
                    else mKeywordFilter?.remove("\"politics\"")
                }
                R.id.fragment_search_checkbox_sports_5 ->{
                    if(checked) mKeywordFilter?.add("\"sports\"")
                    else mKeywordFilter?.remove("\"sports\"")
                }
                R.id.fragment_search_checkbox_travel_6 ->{
                    if(checked) mKeywordFilter?.add("\"travel\"")
                    else mKeywordFilter?.remove("\"travel\"")
                }
            }
        }
    }

    private fun launchSearch(keyword: String?, keywordFilter: String?, beginDate: String?, endDate: String?){
        val callback = activity as ListenerSearch

        callback.launchSearch(keyword, keywordFilter, beginDate, endDate)
    }

}
