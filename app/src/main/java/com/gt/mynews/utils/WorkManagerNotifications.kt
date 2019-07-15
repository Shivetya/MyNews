package com.gt.mynews.utils


import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.gt.mynews.data.repositories.NotificationSettingsArticlesSaved
import com.gt.mynews.usecases.ApiSettingsSaveUseCase
import com.gt.mynews.usecases.ArticlesComparatorUseCase
import com.gt.mynews.usecases.NytUseCaseImpl

class WorkManagerNotifications(context: Context, workParams: WorkerParameters): Worker(context, workParams) {

    private val articlesComparatorUseCase = ArticlesComparatorUseCase(NotificationSettingsArticlesSaved(context),
            NytUseCaseImpl(),
            ApiSettingsSaveUseCase(NotificationSettingsArticlesSaved(context)))

    override fun doWork(): Result {

        val newArticle = articlesComparatorUseCase.launchSearchLastArticlesAndCompareToOldOnes()

        if(newArticle){
            //notif
        }
        else{
            //pas Notif
        }

        return Result.success()
    }

}