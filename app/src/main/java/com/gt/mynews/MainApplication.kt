package com.gt.mynews

import android.app.Application
import android.content.Context

class MainApplication : Application(){

    override fun onCreate() {
        super.onCreate()

        setApplication(this)
    }

    companion object{
        private lateinit var instanceContext: MainApplication

        fun getContext(): Context {
            return instanceContext.applicationContext
        }

        private fun setApplication(mainApplication: MainApplication){
            instanceContext = mainApplication
        }
    }

}