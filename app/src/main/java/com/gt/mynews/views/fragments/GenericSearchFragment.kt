package com.gt.mynews.views.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment

/**
 * A simple [Fragment] subclass.
 *
 */
class GenericSearchFragment : AbsTitledFragment() {

    companion object{

        private const val KEY_KEYWORD = "KEY_KEYWORD"

        fun newInstance(keyWord : String) : GenericSearchFragment {

            val frag = GenericSearchFragment()

            val args = Bundle(1)
            args.putString(KEY_KEYWORD, keyWord)

            frag.arguments = args

            return frag
        }
    }


    override fun getKeyword() :String? = arguments?.getString(KEY_KEYWORD)


}
