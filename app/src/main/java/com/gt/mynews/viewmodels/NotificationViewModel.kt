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
    private val listKeywordFilter: MutableCollection<String> = ArrayList()

    fun saveKeyword(keyword: String?){

        if (keyword != null){
            usecase.saveKeyword(keyword)
        }
    }

    fun getKeywordSaved(): String{
        return usecase.getKeyword()

    }

    fun getKeywordFilterSaved(): Collection<String>{
        return usecase.getKeywordFilter()
    }

    fun onNotificationEnabled(notifEnabled: Boolean){

        if (notifEnabled){

            val notifWorkerBuilder = PeriodicWorkRequest.Builder(NotificationWorker::class.java, 24, TimeUnit.HOURS, 2, TimeUnit.HOURS)
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

    fun addKeywordFilter(keywordFilter: String){

        listKeywordFilter.add(keywordFilter)
        usecase.saveKeywordFilter(listKeywordFilter)
    }

    fun removeKeywordFilter(keywordFilter: String){

        listKeywordFilter.remove(keywordFilter)
        usecase.saveKeywordFilter(listKeywordFilter)
    }
}