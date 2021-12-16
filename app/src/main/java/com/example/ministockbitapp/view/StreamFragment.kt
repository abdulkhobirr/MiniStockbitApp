package com.example.ministockbitapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ministockbitapp.R
import com.example.ministockbitapp.databinding.FragmentStreamBinding
import com.example.ministockbitapp.viewmodel.StreamViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class StreamFragment : Fragment() {
    private var _binding: FragmentStreamBinding ? = null
    private val binding get() = _binding!!

    private val streamViewModel: StreamViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentStreamBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservable()
        streamViewModel.streamCrypto()
    }

    private fun initObservable(){
        streamViewModel.liveCrypto.observe(viewLifecycleOwner, {
            binding.tvBtc.text = it.getTransactions("BTC").last().price.toString()
            Log.d("HargaCryptoUSD", it.getTransactions("BTC").asReversed().toString())
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}