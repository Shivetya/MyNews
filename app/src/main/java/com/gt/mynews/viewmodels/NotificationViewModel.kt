package com.gt.mynews.viewmodels

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.work.*
import com.gt.mynews.MainApplication
import com.gt.mynews.notifications.NotificationWorker
import com.gt.mynews.usecases.ApiSettingsSaveUseCase
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

class NotificationViewModel(private val usecase: ApiSettingsSaveUseCase) : ViewModel() {

    companion object{
        const val TAG_NOTIF = "TAG_NOTIF"
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
        return if (usecase.getKeyword() == null){
            null
        } else {
            usecase.getKeyword()!!.removePrefix("(\"").removeSuffix("\")")
        }

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
            "news_desk:(\"${keywordFilter.joinToString("\" \"")}\")"
        }
        else {
            null
        }
    }

    fun onSwitchTouched(notifEnabled: Boolean){

        if (notifEnabled){

            val notifWorkerBuilder = PeriodicWorkRequest.Builder(NotificationWorker::class.java, 24, TimeUnit.HOURS)
                    .setConstraints(Constraints.NONE)
                    .addTag(TAG_NOTIF)
                    .build()

            val workmanager = WorkManager.getInstance(MainApplication.getContext())
            workmanager.enqueue(notifWorkerBuilder)
        } else {
            WorkManager.getInstance(MainApplication.getContext()).cancelAllWorkByTag(TAG_NOTIF)
        }
    }

    fun isSwitchOn(): Boolean{

        val workInfo = WorkManager.getInstance(MainApplication.getContext())
                .getWorkInfosByTag(TAG_NOTIF)
                .get()

        return if (workInfo != null && workInfo.isNotEmpty()){

            workInfo.last().state == WorkInfo.State.ENQUEUED || workInfo.last().state == WorkInfo.State.RUNNING

        } else {
            false
        }

    }
}