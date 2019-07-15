package com.gt.mynews.viewmodels

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import com.gt.mynews.usecases.ApiSettingsSaveUseCase

class NotificationViewModel(private val usecase: ApiSettingsSaveUseCase) : ViewModel() {

    fun saveKeyword(keyword: String?){

        if (keyword != null){
            usecase.saveKeyword(transformKeywordQueryReady(keyword))
        }
    }

    fun saveKeywordFilter(listKeywordFilter: ArrayList<String>?){

        if (listKeywordFilter != null && listKeywordFilter.isNotEmpty()){
            usecase.saveKeywordFilter(transformKeywordFilterToQueryReady(listKeywordFilter))
        }
    }

    fun getKeywordSaved(): String?{
        return usecase.getKeyword()
    }

    fun getKeywordFilterSaved(): String?{
        return usecase.getKeywordFilter()
    }

    @VisibleForTesting
    internal fun transformKeywordQueryReady(keyword: String?): String?{

        return if(keyword != null){
            "(\"$keyword\")"
        } else {
            null
        }
    }

    @VisibleForTesting
    internal fun transformKeywordFilterToQueryReady(keywordFilter: ArrayList<String>?) : String?{

        return if(keywordFilter != null && keywordFilter.isNotEmpty() ){
            "news_desk:(${keywordFilter.joinToString(" ")})"
        }
        else {
            null
        }
    }

}