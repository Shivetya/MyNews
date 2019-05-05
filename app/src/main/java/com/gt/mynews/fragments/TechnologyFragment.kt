package com.gt.mynews.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.gt.mynews.R

/**
 * A simple [Fragment] subclass.
 *
 */
class TechnologyFragment : Fragment() {

    companion object{

        fun newInstance() : TechnologyFragment = TechnologyFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_technology, container, false)
    }


}
