package com.gt.mynews.utils


import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.gt.mynews.data.repositories.NotificationSettingsArticlesSaved
import com.gt.mynews.usecases.ApiSettingsSaveUseCase
import com.gt.mynews.usecases.ArticlesComparatorUseCase
import com.gt.mynews.usecases.ArticlesComparatorUseCaseInterface
import com.gt.mynews.usecases.NytUseCaseImpl

class NotificationWorker(context: Context, workParams: WorkerParameters): Worker(context, workParams) {

    private var mArticlesComparatorUseCase : ArticlesComparatorUseCaseInterface = ArticlesComparatorUseCase(NotificationSettingsArticlesSaved(context),
            NytUseCaseImpl(),
            ApiSettingsSaveUseCase(NotificationSettingsArticlesSaved(context)))

    var mNotNewArticle: Boolean = false

    override fun doWork(): Result {

        mNotNewArticle = mArticlesComparatorUseCase.isThereNewArticle()

        if(mNotNewArticle){
            //notif
        }
        else{
            //pas Notif
        }

        return Result.success()
    }

    fun setMockedUseCaseForTest(articleComparatorUseCase: ArticlesComparatorUseCaseInterface){
        mArticlesComparatorUseCase = articleComparatorUseCase
    }
}