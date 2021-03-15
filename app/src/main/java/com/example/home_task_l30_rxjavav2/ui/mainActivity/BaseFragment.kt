package com.example.home_task_l30_rxjavav2.ui.mainActivity

import androidx.fragment.app.Fragment

abstract  class BaseFragment(layoutId: Int) : Fragment(layoutId) {
    protected val navigation: Navigator by lazy {
        (requireActivity() as NavigationActivity).navigator
    }
}