package com.gt.mynews.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.gt.mynews.R

/**
 * A simple [Fragment] subclass.
 *
 */
class ScienceFragment : Fragment() {

    companion object{
        fun newInstance() : ScienceFragment = ScienceFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_science, container, false)
    }


}
