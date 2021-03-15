package com.example.home_task_l30_rxjavav2.ui.mainActivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.home_task_l30_rxjavav2.R

class NavigationActivity : AppCompatActivity(R.layout.activity_main) {
    val navigator by lazy {
        Navigator(supportFragmentManager, R.id.main_fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigator.showViewPostsFragment()
    }

}