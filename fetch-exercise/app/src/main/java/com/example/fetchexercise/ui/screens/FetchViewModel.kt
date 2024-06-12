package com.example.fetchexercise.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchexercise.model.JsonItem
import kotlinx.coroutines.launch
import com.example.fetchexercise.network.FetchApi
import java.io.IOException

sealed interface FetchUiState {
    data class Success(val jsonItems: List<JsonItem>) : FetchUiState
    object Error : FetchUiState
    object Loading : FetchUiState
}

class FetchViewModel : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var fetchUiState: FetchUiState by mutableStateOf(FetchUiState.Loading)
        private set

    /**
     * Call getHiringJson() on init so we can display status immediately.
     */
    init {
        getHiringJson()
    }

    /**
     * Gets JSON objects from the Fetch API Retrofit service and updates the UI
     */
    private fun getHiringJson() {
        viewModelScope.launch {
            fetchUiState =  try {
                val listResult = FetchApi.retrofitService.getFetchJson().sorted()
                FetchUiState.Success(listResult);
            } catch (e: IOException) {
                FetchUiState.Error
            }

        }
    }
}
