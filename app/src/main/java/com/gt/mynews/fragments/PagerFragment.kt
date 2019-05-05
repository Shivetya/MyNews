package com.gt.mynews.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment

import com.gt.mynews.R


/**
 * A simple [Fragment] subclass.
 */
class PagerFragment : Fragment() {

    companion object {

        private const val KEY_POSITION = "position"

        fun newInstance(position: Int): PagerFragment {

            val frag = PagerFragment()

            val args = Bundle()
            args.putInt(KEY_POSITION, position)

            frag.arguments = args

            return frag
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val result : View =  inflater.inflate(R.layout.fragment_pager, container, false)

        val rootView : LinearLayout = result.findViewById(R.id.fragment_pager_root)
        val textView : TextView = result.findViewById(R.id.fragment_pager_tv)

        val position : Int? = arguments?.getInt(KEY_POSITION, -1)

        textView.setText("Page number : $position")

        return result
    }


}// Required empty public constructor
