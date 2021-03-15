package com.russmsit.githubclient.di

import com.russmsit.githubclient.domain.repositories.ISearchingReposRepository
import com.russmsit.githubclient.domain.usecases.FindSuitableReposUseCase
import com.russmsit.githubclient.domain.usecases.GetRepoIssuesUseCase
import com.russmsit.githubclient.entities.RepoModel
import com.russmsit.githubclient.github.core.GithubRepository
import com.russmsit.githubclient.github.core.api.GithubApi
import com.russmsit.githubclient.github.core.di.GithubModule
import com.russmsit.githubclient.presentation.detailed.DetailedViewModel
import com.russmsit.githubclient.presentation.search.SearchViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {
    val appModules
        get() = listOf(
            GithubModule.networkModule,
            viewModelsModule,
            repositoriesModule,
            useCasesModule
        )

    private val viewModelsModule = module {
        viewModel<SearchViewModel> {
            SearchViewModel(
                savedStateHandle = get(),
                findSuitableReposUseCase = get<FindSuitableReposUseCase>(),
            )
        }
        viewModel<DetailedViewModel> { (repo: RepoModel) ->
            DetailedViewModel(
                savedStateHandle = get(),
                repo = repo,
                getRepoIssuesUseCase = get<GetRepoIssuesUseCase>(),
                dispatcher = Dispatchers.Default
            )
        }
    }

    private val repositoriesModule = module {
        single<ISearchingReposRepository> { GithubRepository(api = get<GithubApi>(), dispatcher = Dispatchers.IO) }
    }

    private val useCasesModule = module {
        single<FindSuitableReposUseCase> { FindSuitableReposUseCase(searchingRepository = get<ISearchingReposRepository>()) }
        single<GetRepoIssuesUseCase> { GetRepoIssuesUseCase(searchingRepository = get<ISearchingReposRepository>()) }
    }
}