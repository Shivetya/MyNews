package com.gt.mynews.Notifications


import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.gt.mynews.MainApplication
import com.gt.mynews.data.repositories.NotificationSettingsArticlesSaved
import com.gt.mynews.usecases.ApiSettingsSaveUseCase
import com.gt.mynews.usecases.ArticlesComparatorUseCase
import com.gt.mynews.usecases.ArticlesComparatorUseCaseInterface
import com.gt.mynews.usecases.NytUseCaseImpl

class NotificationWorker(context: Context, workParams: WorkerParameters): Worker(context, workParams) {

    private var mArticlesComparatorUseCase : ArticlesComparatorUseCaseInterface = ArticlesComparatorUseCase(NotificationSettingsArticlesSaved(context),
            NytUseCaseImpl(),
            ApiSettingsSaveUseCase(NotificationSettingsArticlesSaved(context)))

    var mIsNewArticle: Boolean = false

    override fun doWork(): Result {

        mIsNewArticle = mArticlesComparatorUseCase.isThereNewArticle()

        if(mIsNewArticle){
            //val notifBuilder = NotificationCompat.Builder(MainApplication.getContext(), CHANNEL_ID)
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