package com.example.ministockbitapp.view.watchlist

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
import com.example.ministockbitapp.databinding.FragmentWatchlistBinding
import com.example.ministockbitapp.utils.*
import com.example.ministockbitapp.utils.viewmodel.Result
import com.example.ministockbitapp.viewmodel.CryptoViewModel
import com.example.ministockbitapp.viewmodel.LoginViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel

class WatchlistFragment : Fragment() {

    private val viewModel: CryptoViewModel by viewModel()
    private val loginViewModel: LoginViewModel by viewModel()
    private lateinit var cryptoAdapter: WatchlistAdapter
    private var _binding: FragmentWatchlistBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWatchlistBinding.inflate(inflater, container, false)
        return binding.root
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
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getCrypto()
            binding.swipeRefresh.isRefreshing = false
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
            setActionBar(binding.toolbarWatchlist)
            actionBar?.setDisplayShowTitleEnabled(false)
        }
        binding.toolbarWatchlist.apply {
            inflateMenu(R.menu.watchlist_toolbar_menu)
            setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.action_document -> {
                        Toast.makeText(requireActivity(), "Notification", Toast.LENGTH_SHORT).show()
                    }
                    R.id.action_logout -> {
                        context.showWhiteAlertDialog(
                                title = context.getString(R.string.title_logout),
                                message = context.getString(R.string.message_confirm_logout),
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

        binding.rvCrypto.apply {
            layoutManager =
                LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            setHasFixedSize(true)
            adapter = cryptoAdapter
        }

        binding.rvCrypto.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    binding.swipeRefresh.post {
                        binding.swipeRefresh.isRefreshing = true
                    }

                    viewModel.incrementPageCount()
                    viewModel.getCrypto()
                }
            }
        })
    }

    private fun setupObservables(){
        viewModel.listCrypto.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Result.Loading -> {
                    if (viewModel.pageCount == 0) binding.msvCryptoList.showLoadingState()
                }
                is Result.Failure -> {
                }
                is Result.Empty -> {
                    binding.msvCryptoList.showEmptyState()
                }
                is Result.Success -> {
                    binding.msvCryptoList.showDefaultState()

                    if (viewModel.pageCount == 0){
                        cryptoAdapter.setCryptoData(it.data.data)
                    } else {
                        binding.swipeRefresh.post {
                            binding.swipeRefresh.isRefreshing = false
                        }
                        cryptoAdapter.loadMoreData(it.data.data)
                    }
                }
            }
        })
    }
}