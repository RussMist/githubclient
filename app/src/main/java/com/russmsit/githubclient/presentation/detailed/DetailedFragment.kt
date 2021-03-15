package com.russmsit.githubclient.presentation.detailed

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import coil.transform.CircleCropTransformation
import com.google.android.material.snackbar.Snackbar
import com.russmsit.githubclient.R
import com.russmsit.githubclient.databinding.DetailedFragmentBinding
import com.russmsit.githubclient.entities.IssueModel
import com.russmsit.githubclient.entities.RepoModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class DetailedFragment : Fragment() {
    private val args: DetailedFragmentArgs by navArgs()
    private val repo: RepoModel by lazy { args.repo }
    private val viewModel: DetailedViewModel by viewModel { parametersOf(repo) }

    private var _binding: DetailedFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailedFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title = "${repo.userName}/${repo.name}"
        activity?.title = title


        with(binding) {
            repoTitle.text = title
            repoDescription.text = repo.description
            ownerAvatar.load(repo.userAvatarUrl) {
                transformations(CircleCropTransformation())
                crossfade(true)
                placeholder(ColorDrawable(ContextCompat.getColor(binding.root.context, R.color.green_200)))
            }
        }

        viewModel.state.onEach { state ->
            when (state) {
                is DetailedViewModel.State.Loaded -> handleUiState(false, state.issues)
                DetailedViewModel.State.Loading -> handleUiState(true)
                DetailedViewModel.State.None -> handleUiState(false)
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.event.onEach { event ->
            when (event) {
                is DetailedViewModel.Event.Error -> lifecycleScope.launch(Dispatchers.Main) {
                    Snackbar.make(
                        binding.root,
                        event.message,
                        Snackbar.LENGTH_LONG
                    ).show()
                }

            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun handleUiState(isLoading: Boolean, issues: List<IssueModel>? = null) {
        lifecycleScope.launch(Dispatchers.Main) {
            showShimmer(isLoading)
            showIssuesLayout(issues)
            showNoIssuesLayout(!isLoading && issues.isNullOrEmpty())
        }
    }

    private fun showShimmer(isShowShimmer: Boolean) {
        with(binding.shimmerLayout) {
            if (isShowShimmer) startShimmer() else stopShimmer()
            visibility = if (isShowShimmer) View.VISIBLE else View.GONE
        }
    }

    private fun showIssuesLayout(issues: List<IssueModel>?) {
        with(binding.issuesList) {
            if (!issues.isNullOrEmpty()) {
                layoutManager = LinearLayoutManager(context)
                adapter = IssuesAdapter(issues)
                addItemDecoration(
                    DividerItemDecoration(
                        context,
                        DividerItemDecoration.VERTICAL
                    )
                )
                visibility = View.VISIBLE
            } else {
                visibility = View.GONE
            }
        }
    }

    private fun showNoIssuesLayout(isShowErrorLayout: Boolean) {
        with(binding.noIssuesGroup) {
            visibility = if (isShowErrorLayout) View.VISIBLE else View.GONE
        }
    }
}
