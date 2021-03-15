package com.russmsit.githubclient.github.core.api

import com.russmsit.githubclient.github.core.models.Issue
import com.russmsit.githubclient.github.core.models.SuitableReposResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {
    /**
     * @see <a href="https://docs.github.com/en/rest/reference/search#search-repositories">Search repo API</a>
     */
    @GET("/search/repositories")
    suspend fun findSuitableRepos(
        @Query("q") searchedQuery: String,
        @Query("page") pageNumber: Int,
        @Query("per_page") perPage: Int,
        @Query("sort") sort: String
    ): SuitableReposResponse


    /**
     * @see <a href="https://docs.github.com/en/rest/reference/issues#list-repository-issues">List repository issues</a>
     */
    @GET("/repos/{owner}/{repo}/issues")
    suspend fun getRepoIssues(
        @Path("owner") ownerName: String,
        @Path("repo") repoName: String,
        @Query("per_page") perPage: Int,
        @Query("sort") sort: String
    ): List<Issue>
}