package com.russmsit.githubclient.presentation.detailed

import android.os.Parcelable
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.russmsit.githubclient.domain.enteties.asSuccess
import com.russmsit.githubclient.domain.enteties.isSuccess
import com.russmsit.githubclient.domain.usecases.GetRepoIssuesUseCase
import com.russmsit.githubclient.entities.IssueModel
import com.russmsit.githubclient.entities.RepoModel
import com.russmsit.githubclient.mappers.toIssueModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize

class DetailedViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val repo: RepoModel,
    private val getRepoIssuesUseCase: GetRepoIssuesUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _state: MutableStateFlow<State> = MutableStateFlow(savedStateHandle[STATE_KEY] ?: State.None)
    val state: StateFlow<State> = _state.asStateFlow()

    private val _event = MutableSharedFlow<Event>(replay = 0, extraBufferCapacity = 0)
    val event: SharedFlow<Event> = _event

    init {
        loadIssues()
    }

    private fun loadIssues() {
        viewModelScope.launch(dispatcher) {
            _state.emit(State.Loading)

            var errorMessage = ""

            try {
                val result = getRepoIssuesUseCase(repo.userName, repo.name)

                if (result.isSuccess) {
                    val data = result.asSuccess().data
                    if (data.isEmpty()) {
                        errorMessage = "No available issues"
                    } else {
                        _state.emit(State.Loaded(data.map { issue -> issue.toIssueModel() }))
                        return@launch
                    }
                }

            } catch (exception: Exception) {
                errorMessage = exception.message ?: "Unknown error"
            }

            _state.emit(State.None)
            _event.emit(Event.Error(errorMessage))
        }
    }

    sealed class State : Parcelable {
        @Parcelize
        object None : State()

        @Parcelize
        object Loading : State()

        @Parcelize
        data class Loaded(val issues: List<IssueModel>) : State()
    }

    sealed class Event {
        data class Error(val message: String) : Event()
    }

    override fun onCleared() {
        savedStateHandle[STATE_KEY] = _state.value
        super.onCleared()
    }

    companion object {
        private const val STATE_KEY = "STATE_KEY"
    }
}