package com.sanathcoding.dictionaryapp.feature_dictionary.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanathcoding.dictionaryapp.core.util.Resource
import com.sanathcoding.dictionaryapp.feature_dictionary.domain.use_case.GetWordInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class WordInfoViewModel @Inject constructor(
    private val getWordInfo: GetWordInfo,
    private val dispatcher: CoroutineDispatcher,
): ViewModel() {

    private val fetchDataStoreSearchDataHandler = CoroutineExceptionHandler { _, throwable ->
        println("FetchWordInfoSearchHandler Exception $throwable")
    }

    var searchQuery by mutableStateOf("")
        private set

    var state by mutableStateOf(WordInfoState())
        private set

    private val _uiEvent =  MutableSharedFlow<UIEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    private var searchJob: Job? = null

    fun onSearch(query: String) {
        searchQuery = query
        searchJob?.cancel()
        searchJob = viewModelScope.launch(dispatcher + fetchDataStoreSearchDataHandler) {
            delay(500L)
            getWordInfo(query)
                .onEach { resource ->
                    when(resource) {
                        is Resource.Error -> {
                            state.wordInfoItems = resource.data ?: emptyList()
                            state.isLoading = false
                            _uiEvent.emit(UIEvent.ShowSnackBar("Unknown Error"))
                        }
                        is Resource.Loading -> {
                            state.wordInfoItems = resource.data ?: emptyList()
                            state.isLoading = true
                        }
                        is Resource.Success -> {
                            state.wordInfoItems = resource.data ?: emptyList()
                            state.isLoading = false
                        }
                    }
                }.launchIn(this)
        }
    }

}
