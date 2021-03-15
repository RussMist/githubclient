package com.russmsit.githubclient

import androidx.navigation.NavController
import com.russmsit.githubclient.domain.enteties.RepoEntity
import com.russmsit.githubclient.entities.RepoModel
import com.russmsit.githubclient.presentation.search.SearchFragmentDirections

object SimpleRouter {
    fun navigateToDetailedScreen(navController: NavController, repo: RepoModel) {
        val action = SearchFragmentDirections.actionOpenDetailedScreen(repo)
        navController.navigate(action)
    }
}