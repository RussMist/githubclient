package com.russmsit.githubclient.presentation.search.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.russmsit.githubclient.domain.enteties.ReposRequest
import com.russmsit.githubclient.domain.enteties.asFailure
import com.russmsit.githubclient.domain.enteties.asSuccess
import com.russmsit.githubclient.domain.enteties.isSuccess
import com.russmsit.githubclient.domain.usecases.FindSuitableReposUseCase
import com.russmsit.githubclient.entities.RepoModel
import com.russmsit.githubclient.mappers.toRepoModel

class RepoPagingDataSource(
    private val useCase: FindSuitableReposUseCase,
    private val query: String
) : PagingSource<Int, RepoModel>() {
    override fun getRefreshKey(state: PagingState<Int, RepoModel>): Int = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepoModel> {
        val pageSize = params.loadSize
        val page = params.key ?: 0

        return try {
            val request = ReposRequest(searchingQuery = query, pageNumber = page, perPage = pageSize)
            val data = useCase(request)

            if (data.isSuccess) {
                val result = data.asSuccess()
                val nextKey = if (result.data.size == pageSize) page.plus(1) else null
                LoadResult.Page(result.data.map { repo -> repo.toRepoModel() }, null, nextKey)
            } else {
                val result = data.asFailure()
                LoadResult.Error(result.cause ?: Throwable(result.message))
            }
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }


}