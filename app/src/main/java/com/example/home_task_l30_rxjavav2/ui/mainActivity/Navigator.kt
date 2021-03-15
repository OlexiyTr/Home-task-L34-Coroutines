package com.example.home_task_l30_rxjavav2.ui.mainActivity

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import com.example.home_task_l30_rxjavav2.ui.inputNewPostScreen.InputNewPostFragment
import com.example.home_task_l30_rxjavav2.ui.showPostsScreen.PostsScreenFragment

class Navigator(
    private val fragmentManager: FragmentManager,
    @IdRes private val container: Int
) {
    companion object {
        private const val ADD_POST_FRAGMENT = "ADD_POST_FRAGMENT"
    }


    fun showAddPostFragment() {
        fragmentManager.beginTransaction()
            .add(container, InputNewPostFragment.newInstance())
            .addToBackStack(ADD_POST_FRAGMENT)
            .commit()
    }

    fun showViewPostsFragment() {
        fragmentManager.beginTransaction()
            .replace(container, PostsScreenFragment.newInstance())
            .commit()
    }
}