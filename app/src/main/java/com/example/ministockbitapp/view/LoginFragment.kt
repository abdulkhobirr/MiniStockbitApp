package com.example.ministockbitapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.ministockbitapp.R
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {
    val mainNavController: NavController? by lazy { activity?.findNavController(R.id.main_navigation) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initActions()
    }

    private fun initActions(){
        btnLogin.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.watchlistFragment))
    }
}