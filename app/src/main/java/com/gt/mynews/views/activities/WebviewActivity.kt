package com.gt.mynews.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gt.mynews.R
import kotlinx.android.synthetic.main.activity_webview.*

class WebviewActivity : AppCompatActivity() {

    companion object{
        const val KEY_URL = "key_Url"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        val intent = intent
        val keyUrl = intent.getStringExtra(KEY_URL)

        activity_webview_article.loadUrl(keyUrl)
    }
}
