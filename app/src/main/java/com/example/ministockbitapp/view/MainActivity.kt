package com.example.ministockbitapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.example.ministockbitapp.R
import com.example.ministockbitapp.utils.gone
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val navController: NavController by lazy {
        Navigation.findNavController(this, R.id.homeFragmentContainer)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNav.setupWithNavController(navController)

        bottomNav.gone()
    }
}