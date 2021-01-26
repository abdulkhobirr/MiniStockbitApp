package com.example.ministockbitapp.view.watchlist

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ministockbitapp.R
import com.example.ministockbitapp.data.crypto.model.Data
import com.example.ministockbitapp.utils.showDefaultState
import com.example.ministockbitapp.utils.showEmptyState
import com.example.ministockbitapp.utils.showLoadingState
import com.example.ministockbitapp.utils.viewmodel.Result
import com.example.ministockbitapp.utils.visible
import com.example.ministockbitapp.viewmodel.CryptoViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_watchlist.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class WatchlistFragment : Fragment() {

    private val viewModel: CryptoViewModel by viewModel()
    private lateinit var cryptoAdapter: WatchlistAdapter
    private var listCrypto = listOf<Data>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_watchlist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getCrypto(0)

        setupNav()

        setupToolbar()

        setupRV()

        setupObservables()
    }

    private fun setupNav(){
        val bottomNavigation = activity?.window?.findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNavigation?.visible()
    }

    private fun setupToolbar(){
        requireActivity().apply {
            setActionBar(toolbarWatchlist)
            actionBar?.setLogo(R.drawable.stockbit)
            actionBar?.setDisplayShowTitleEnabled(false)
        }
        toolbarWatchlist.apply {
            inflateMenu(R.menu.watchlist_toolbar_menu)
            setOnMenuItemClickListener {
                Toast.makeText(requireActivity(), "AAAA", Toast.LENGTH_SHORT).show()
                true
            }
        }
    }

    private fun setupRV(){
        cryptoAdapter = WatchlistAdapter()

        rvCrypto.apply {
            layoutManager =
                LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            setHasFixedSize(true)
            adapter = cryptoAdapter
        }
    }

    private fun setupObservables(){
        viewModel.listCrypto.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Result.Loading -> {
                    msvCryptoList.showLoadingState()
                }
                is Result.Failure -> {
                }
                is Result.Empty -> {
                    msvCryptoList.showEmptyState()
                }
                is Result.Success -> {
                    msvCryptoList.showDefaultState()
                    Toast.makeText(requireActivity(), "Success", Toast.LENGTH_SHORT).show()

                    Log.d("DATAAA", it.data.data.toString())

                    listCrypto = it.data.data

                    Log.d("DataCrypto", listCrypto.toString())


                    cryptoAdapter.setCryptoData(listCrypto)
                }
            }
        })
    }
}