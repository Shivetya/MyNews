package com.gt.mynews.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.gt.mynews.MainApplication

class ChannelNotificationManager {

    companion object {
        private const val CHANNEL_NAME = "New Article"
        private const val CHANNEL_TEXT = "There is at least one new article you want to see !"
        const val CHANNEL_ID = "NOTIF_NEW_ARTICLE"


        fun createNotificationChannel() {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val name = CHANNEL_NAME
                val descriptionText = CHANNEL_TEXT
                val importance = NotificationManager.IMPORTANCE_HIGH

                val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                    description = descriptionText
                }
                // Register the channel with the system
                val notificationManager: NotificationManager =
                        MainApplication.getContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(channel)
            }
        }
    }
}