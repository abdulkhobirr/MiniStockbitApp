package com.example.ministockbitapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ministockbitapp.R
import com.example.ministockbitapp.databinding.FragmentStreamBinding

class StreamFragment : Fragment() {
    private var _binding: FragmentStreamBinding ? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStreamBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}