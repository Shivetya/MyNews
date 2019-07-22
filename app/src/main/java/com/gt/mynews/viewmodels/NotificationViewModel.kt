package com.gt.mynews.viewmodels

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.work.*
import com.gt.mynews.MainApplication
import com.gt.mynews.notifications.NotificationWorker
import com.gt.mynews.usecases.ApiSettingsSaveUseCase
import java.util.concurrent.TimeUnit

class NotificationViewModel(private val usecase: ApiSettingsSaveUseCase) : ViewModel() {

    companion object{
        private const val TAG_NOTIF = "TAG_NOTIF"
    }

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

    fun onSwitchTouched(notifEnabled: Boolean){

        if (notifEnabled){

            val constraintsForNotif = Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()

            val notifWorkerBuilder = PeriodicWorkRequestBuilder<NotificationWorker>(15, TimeUnit.MINUTES)
                    .setConstraints(constraintsForNotif)
                    .addTag(TAG_NOTIF)
                    .build()

            WorkManager.getInstance(MainApplication.getContext()).enqueue(notifWorkerBuilder)
        } else {
            WorkManager.getInstance(MainApplication.getContext()).cancelAllWorkByTag(TAG_NOTIF)
        }
    }

}