package com.gt.mynews.views.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gt.mynews.R


class SearchItemsFragment : Fragment() {

    companion object {
        fun newInstance() : SearchItemsFragment {
            return SearchItemsFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search_items, container,false)
    }


}
