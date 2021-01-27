package com.example.ministockbitapp.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.ministockbitapp.R
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {
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

        btnFacebookSignIn.setOnClickListener {
            Toast.makeText(requireActivity(), "Facebook Sign In", Toast.LENGTH_SHORT).show()
        }

        btnGoogleSignIn.setOnClickListener {
            Toast.makeText(requireActivity(), "Google Sign In", Toast.LENGTH_SHORT).show()
        }

        btnLupaPassword.setOnClickListener {
            Toast.makeText(requireActivity(), "Lupa Password", Toast.LENGTH_SHORT).show()
        }

        btnLoginFingerprint.setOnClickListener {
            Toast.makeText(requireActivity(), "Fingerprint Sign In", Toast.LENGTH_SHORT).show()
        }

        btnDaftarSekarang.setOnClickListener {
            Toast.makeText(requireActivity(), "Daftar", Toast.LENGTH_SHORT).show()
        }
    }
}