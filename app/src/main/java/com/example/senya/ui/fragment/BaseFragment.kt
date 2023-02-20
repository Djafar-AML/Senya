package com.example.senya.ui.fragment

import androidx.fragment.app.Fragment
import com.example.senya.ui.MainActivity

abstract class BaseFragment : Fragment() {


    protected val activityHandler by lazy {
        (activity as MainActivity)
    }

    protected val navController by lazy {
        activityHandler.navController
    }

    protected val attractions by lazy {
        activityHandler.attractionList
    }

}