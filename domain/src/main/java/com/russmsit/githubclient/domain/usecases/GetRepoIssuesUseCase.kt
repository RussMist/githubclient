package com.russmsit.githubclient.domain.usecases

import com.russmsit.githubclient.domain.repositories.ISearchingReposRepository

class GetRepoIssuesUseCase(private val searchingRepository: ISearchingReposRepository) {
    suspend operator fun invoke(ownerName: String, repoName: String, perPage: Int = 30, sort: String = "updated") =
        searchingRepository.getRepoIssues(ownerName, repoName, perPage, sort)
}