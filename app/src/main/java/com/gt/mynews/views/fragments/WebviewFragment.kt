package com.gt.mynews.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment

class WebviewFragment : Fragment(){

    companion object{

        private const val KEY_URL = "key_url"

        fun newInstance(url : String) : WebviewFragment{
            val frag = WebviewFragment()

            val args = Bundle(1)
            args.putString(KEY_URL, url)

            frag.arguments = args

            return frag
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val webView = WebView(activity)

        webView.loadUrl(arguments?.getString(KEY_URL))

        return super.onCreateView(inflater, container, savedInstanceState)
    }
}