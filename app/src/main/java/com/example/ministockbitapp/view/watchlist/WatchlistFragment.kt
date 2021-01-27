package com.example.ministockbitapp.view.watchlist

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ministockbitapp.R
import com.example.ministockbitapp.utils.*
import com.example.ministockbitapp.utils.pref.PrefManager
import com.example.ministockbitapp.utils.viewmodel.Result
import com.example.ministockbitapp.viewmodel.CryptoViewModel
import com.example.ministockbitapp.viewmodel.LoginViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_watchlist.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class WatchlistFragment : Fragment() {

    private val viewModel: CryptoViewModel by viewModel()
    private val loginViewModel: LoginViewModel by viewModel()
    private lateinit var cryptoAdapter: WatchlistAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_watchlist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getCrypto()

        setupToolbar()
        setupNav()
        setupActions()
        setupRV()
        setupObservables()
    }

    private fun setupActions(){
        swipeRefresh.setOnRefreshListener {
            viewModel.getCrypto()
            swipeRefresh.isRefreshing = false
        }
    }

    private fun setupNav(){
        val bottomNavigation = activity?.window?.findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNavigation?.visible()
    }

    override fun onResume() {
        val bottomNavigation = activity?.window?.findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNavigation?.visible()
        super.onResume()
    }

    private fun setupToolbar(){
        requireActivity().apply {
            setActionBar(toolbarWatchlist)
            actionBar?.setDisplayShowTitleEnabled(false)
        }
        toolbarWatchlist.apply {
            inflateMenu(R.menu.watchlist_toolbar_menu)
            setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.action_document -> {
                        Toast.makeText(requireActivity(), "Notification", Toast.LENGTH_SHORT).show()
                    }
                    R.id.action_logout -> {
                        Toast.makeText(requireActivity(), "Logout", Toast.LENGTH_SHORT).show()
                        context.showWhiteAlertDialog(
                                title = "Logout",
                                message = "Anda yakin ingin logout ?",
                                negativeButton = Pair(getString(android.R.string.cancel), {}),
                                positiveButton = Pair(getString(android.R.string.ok), {
                                    loginViewModel.logout()
                                    findNavController().navigate(R.id.loginFragment)
                                })
                        )
                    }
                }
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

        rvCrypto.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    swipeRefresh.post {
                        swipeRefresh.isRefreshing = true
                    }

                    viewModel.incrementPageCount()
                    viewModel.getCrypto()

                    Toast.makeText(requireActivity(), "Scroll Load", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setupObservables(){
        viewModel.listCrypto.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Result.Loading -> {
                    if (viewModel.pageCount == 0) msvCryptoList.showLoadingState()
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
//                    Log.d("DataCrypto", listCrypto.toString())
                    if (viewModel.pageCount == 0){
                        cryptoAdapter.setCryptoData(it.data.data)
                    } else {
                        swipeRefresh.post {
                            swipeRefresh.isRefreshing = false
                        }
                        cryptoAdapter.loadMoreData(it.data.data)
                    }

                }
            }
        })
    }
}