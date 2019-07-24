package com.gt.mynews.notifications


import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.gt.mynews.MainApplication
import com.gt.mynews.R
import com.gt.mynews.data.repositories.NotificationSettingsArticlesSaved
import com.gt.mynews.notifications.ChannelNotificationManager.Companion.CHANNEL_ID
import com.gt.mynews.usecases.ApiSettingsSaveUseCase
import com.gt.mynews.usecases.ArticlesComparatorUseCase
import com.gt.mynews.usecases.ArticlesComparatorUseCaseInterface
import com.gt.mynews.usecases.NytUseCaseImpl
import com.gt.mynews.views.activities.MainActivity

class NotificationWorker(private val context: Context, workParams: WorkerParameters): Worker(context, workParams) {

    private var mArticlesComparatorUseCase : ArticlesComparatorUseCaseInterface = ArticlesComparatorUseCase(NotificationSettingsArticlesSaved(context),
            NytUseCaseImpl(),
            ApiSettingsSaveUseCase(NotificationSettingsArticlesSaved(context)))

    var mIsNewArticle: Boolean = false

    override fun doWork(): Result {

        mIsNewArticle = mArticlesComparatorUseCase.isThereNewArticle()

        val pendingIntent = PendingIntent.getActivity(context, 0, Intent(context, MainActivity::class.java), PendingIntent.FLAG_UPDATE_CURRENT)

        if(mIsNewArticle){
            val notif = NotificationCompat.Builder(MainApplication.getContext(), CHANNEL_ID)
                    .setSmallIcon(R.drawable.newyorktime_logo)
                    .setContentTitle("New article to see !")
                    .setContentText("Hi ! There is at least one new article to read ! ;)")
                    .setContentIntent(pendingIntent)
                    .build()

            val notifManager = ContextCompat.getSystemService(MainApplication.getContext(), NotificationManager::class.java) as NotificationManager

            notifManager.notify(42, notif)

        } else {

            Log.i(NotificationWorker::class.java.simpleName, "No new article!")

        }

        return Result.success()
    }
}