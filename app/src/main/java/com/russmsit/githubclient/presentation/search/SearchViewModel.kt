package com.russmsit.githubclient.presentation.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.russmsit.githubclient.SimpleRouter
import com.russmsit.githubclient.domain.usecases.FindSuitableReposUseCase
import com.russmsit.githubclient.entities.RepoModel
import com.russmsit.githubclient.presentation.search.paging.RepoPagingDataSource

class SearchViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val findSuitableReposUseCase: FindSuitableReposUseCase,
) : ViewModel() {
    val flow by lazy {
        Pager(PagingConfig(pageSize = 30, enablePlaceholders = true)) {
            RepoPagingDataSource(findSuitableReposUseCase, lastSavedQuery)
                .also { repoPagingDataSource -> lastRepoPagingDataSource = repoPagingDataSource }
        }.flow.cachedIn(viewModelScope)
    }

    private var lastSavedQuery: String = ""
    private var lastRepoPagingDataSource: RepoPagingDataSource? = null

    fun onTextChanged(query: String) {
        if (query != lastSavedQuery) {
            lastSavedQuery = query
            lastRepoPagingDataSource?.invalidate()
        }
    }

    override fun onCleared() {
        super.onCleared()
        lastRepoPagingDataSource?.invalidate()
        lastRepoPagingDataSource = null
    }

    fun handleClick(navController: NavController, model: RepoModel) {
        SimpleRouter.navigateToDetailedScreen(navController, model)
    }
}