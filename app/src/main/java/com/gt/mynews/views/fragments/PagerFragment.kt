package com.gt.mynews.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager

import com.gt.mynews.R
import com.gt.mynews.views.adapters.PageAdapter

class PagerFragment : Fragment() {

    interface SetupViewPagerInterface{
        fun setupViewPager(viewPager: ViewPager)
    }

    companion object{
        fun newInstance() : PagerFragment{
            return PagerFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_pager, container, false)

        val viewPager = view.findViewById<ViewPager>(R.id.fragment_pager_view_pager)
        viewPager.adapter = PageAdapter(childFragmentManager)

        val callback = activity as SetupViewPagerInterface
        callback.setupViewPager(viewPager)

        return view
    }


}
