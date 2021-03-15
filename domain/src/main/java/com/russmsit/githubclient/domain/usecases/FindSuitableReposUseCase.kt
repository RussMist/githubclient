package com.russmsit.githubclient.domain.usecases

import com.russmsit.githubclient.domain.enteties.ReposRequest
import com.russmsit.githubclient.domain.repositories.ISearchingReposRepository

class FindSuitableReposUseCase(private val searchingRepository: ISearchingReposRepository) {
    suspend operator fun invoke(request: ReposRequest) = with(request) {
        searchingRepository.findSuitableRepos(searchingQuery, pageNumber, perPage, sort)
    }
}