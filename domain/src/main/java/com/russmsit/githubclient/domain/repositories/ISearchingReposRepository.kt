package com.russmsit.githubclient.domain.repositories

import com.russmsit.githubclient.domain.enteties.IssueEntity
import com.russmsit.githubclient.domain.enteties.RepoEntity
import com.russmsit.githubclient.domain.enteties.Result

interface ISearchingReposRepository {
    suspend fun findSuitableRepos(searchingQuery: String, pageNumber: Int, perPage: Int, sort: String): Result<List<RepoEntity>>
    suspend fun getRepoIssues(ownerName: String, repoName: String, perPage: Int, sort: String): Result<List<IssueEntity>>
}