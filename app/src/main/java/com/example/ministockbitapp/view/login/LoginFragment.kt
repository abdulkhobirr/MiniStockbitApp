package com.example.ministockbitapp.view.login

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ministockbitapp.R
import com.example.ministockbitapp.databinding.FragmentLoginBinding
import com.example.ministockbitapp.utils.gone
import com.example.ministockbitapp.viewmodel.LoginViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {
    private val loginViewModel: LoginViewModel by viewModel()
    private var _binding : FragmentLoginBinding ? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        isLoggedIn()

        setupActions()
    }

    override fun onAttach(context: Context) {
        val bottomNavigation = activity?.window?.findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNavigation?.gone()
        super.onAttach(context)
    }

    private fun isLoggedIn(){
        if (loginViewModel.isLoggedIn()){
            findNavController().navigate(R.id.watchlistFragment)
        }
    }

    private fun setupActions(){
        binding.btnLogin.setOnClickListener{
            binding.btnLogin.text = getString(R.string.message_logging_in)
            Handler(Looper.getMainLooper()).postDelayed({
                binding.btnLogin.text = getString(R.string.message_success)
                loginViewModel.login(binding.edtUsername.text.toString())
                findNavController().navigate(R.id.watchlistFragment)
            }, 1500)
        }

        binding.btnFacebookSignIn.setOnClickListener {
            Toast.makeText(requireActivity(), "Facebook Sign In", Toast.LENGTH_SHORT).show()
        }

        binding.btnGoogleSignIn.setOnClickListener {
            Toast.makeText(requireActivity(), "Google Sign In", Toast.LENGTH_SHORT).show()
        }

        binding.btnLupaPassword.setOnClickListener {
            Toast.makeText(requireActivity(), "Lupa Password", Toast.LENGTH_SHORT).show()
        }

        binding.btnLoginFingerprint.setOnClickListener {
            Toast.makeText(requireActivity(), "Fingerprint Sign In", Toast.LENGTH_SHORT).show()
        }

        binding.btnDaftarSekarang.setOnClickListener {
            Toast.makeText(requireActivity(), "Daftar", Toast.LENGTH_SHORT).show()
        }
    }
}