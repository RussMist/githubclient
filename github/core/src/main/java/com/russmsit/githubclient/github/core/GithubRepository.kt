package com.russmsit.githubclient.github.core

import com.russmsit.githubclient.domain.enteties.IssueEntity
import com.russmsit.githubclient.domain.enteties.RepoEntity
import com.russmsit.githubclient.domain.enteties.Result
import com.russmsit.githubclient.domain.repositories.ISearchingReposRepository
import com.russmsit.githubclient.github.core.api.GithubApi
import com.russmsit.githubclient.github.core.mappers.IssueEntityMapper
import com.russmsit.githubclient.github.core.mappers.RepoEntityMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GithubRepository(
    private val api: GithubApi,
    private val dispatcher: CoroutineDispatcher,
    private val repoMapper: RepoEntityMapper = RepoEntityMapper,
    private val issueMapper: IssueEntityMapper = IssueEntityMapper,
) : ISearchingReposRepository {
    override suspend fun findSuitableRepos(searchingQuery: String, pageNumber: Int, perPage: Int, sort: String): Result<List<RepoEntity>> =
        withContext(dispatcher) {
            try {
                val response = api.findSuitableRepos(searchingQuery, pageNumber, perPage, sort)
                val result = response.items
                        ?.map { repo -> repoMapper.toRepoEntity(repo) } ?: throw Exception("empty data")

                Result.Success(result)
            } catch (exception: Exception) {
                Result.Failure(exception.message ?: "Network problem", exception)
            }
        }

    override suspend fun getRepoIssues(ownerName: String, repoName: String, perPage: Int, sort: String): Result<List<IssueEntity>> =
        withContext(dispatcher) {
            try {
                val result =
                    api.getRepoIssues(ownerName, repoName, perPage, sort)
                        .map { issue -> issueMapper.toIssueEntity(issue) }

                Result.Success(result)
            } catch (exception: Exception) {
                Result.Failure(exception.message ?: "Network problem", exception)
            }
        }
}