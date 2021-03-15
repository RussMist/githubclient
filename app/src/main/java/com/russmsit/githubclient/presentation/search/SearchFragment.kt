package com.russmsit.githubclient.presentation.search

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.russmsit.githubclient.R
import com.russmsit.githubclient.databinding.SearchFragmentBinding
import com.russmsit.githubclient.entities.RepoModel
import com.russmsit.githubclient.presentation.search.paging.RepoAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {
    private val viewModel: SearchViewModel by viewModel()

    private var _binding: SearchFragmentBinding? = null
    private val binding get() = _binding!!

    private val repoAdapter = RepoAdapter(::onItemClick)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SearchFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = getString(R.string.app_name)

        binding.repoRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = repoAdapter
        }

        repoAdapter.loadStateFlow.onEach { state ->
            val isDataLoaded = repoAdapter.itemCount > 0

            state.source.forEach { _, loadState ->
                if (loadState is LoadState.Error) {
                    val message = loadState.error.message ?: "Unknown error"
                    showErrorMessage(message)
                }
            }

            when (state.refresh) {
                is LoadState.NotLoading -> handleUIState(isLoading = false, isError = false, isDataLoaded)
                LoadState.Loading -> handleUIState(isLoading = true, isError = false, false)
                is LoadState.Error -> handleUIState(isLoading = false, isError = true, isDataLoaded)
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)

        val searchView = menu.findItem(R.id.search_repo).actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            //TODO: Accept new query only after the user submits it.
            // Handle new character is not acceptable with current Github restrictions (10 per minute for repositories)
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.onTextChanged(query ?: "")

                lifecycleScope.launchWhenStarted {
                    viewModel.flow.collectLatest { pagingData ->
                        repoAdapter.submitData(pagingData)
                    }
                }

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean = false
        })
    }

    private fun onItemClick(model: RepoModel) {
        viewModel.handleClick(findNavController(), model)
    }

    private fun handleUIState(isLoading: Boolean, isError: Boolean, isDataLoaded: Boolean) {
        showDataLayout(isDataLoaded)
        showShimmer(isLoading)
        showErrorLayout(isError && !isDataLoaded)
        showInputLayout(!isLoading && !isError && !isDataLoaded)
    }

    private fun showShimmer(isShowShimmer: Boolean) {
        with(binding.shimmerLayout) {
            if (isShowShimmer) startShimmer() else stopShimmer()
            visibility = if (isShowShimmer) View.VISIBLE else View.GONE
        }
    }

    private fun showErrorLayout(isShowErrorLayout: Boolean) {
        with(binding.errorLayout.root) {
            visibility = if (isShowErrorLayout) View.VISIBLE else View.GONE
        }
    }

    private fun showDataLayout(isShowDataLayout: Boolean) {
        with(binding.repoRecyclerView) {
            visibility = if (isShowDataLayout) View.VISIBLE else View.GONE
        }
    }

    private fun showInputLayout(isShowInputLayout: Boolean) {
        with(binding.inputDataLayout.root) {
            visibility = if (isShowInputLayout) View.VISIBLE else View.GONE
        }
    }

    private fun showErrorMessage(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }
}
