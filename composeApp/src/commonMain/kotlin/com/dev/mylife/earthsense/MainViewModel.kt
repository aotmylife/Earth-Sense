package com.dev.mylife.earthsense

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.mylife.earthsense.network.domain.model.EarthquakeModel
import com.dev.mylife.earthsense.network.domain.usecase.GetEarthquakesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val getEarthquakesUseCase: GetEarthquakesUseCase
) : ViewModel() {

    private val _helloText = MutableStateFlow("")
    val helloText = _helloText.asStateFlow()

    var countNumber: Int = 0
        private set

    private val _earthquakes = MutableStateFlow<List<EarthquakeModel>>(emptyList())
    val earthquakes = _earthquakes.asStateFlow()

    private val _uiState = MutableStateFlow<EarthquakeUiState>(EarthquakeUiState.Loading)
    val uiState: StateFlow<EarthquakeUiState> = _uiState.asStateFlow()
    
    init {
        loadEarthquakes()
    }

    fun loadEarthquakes() {
        viewModelScope.launch {
            _uiState.value = EarthquakeUiState.Loading

            getEarthquakesUseCase().onSuccess { list ->
                _uiState.value = EarthquakeUiState.Success(list)
            }.onFailure { error ->
                _uiState.value = EarthquakeUiState.Error(error.message ?: "Unknown Error")
            }
        }
    }

    fun updateText(text: String) {
        viewModelScope.launch {
            countNumber++
            _helloText.emit("$text count : $countNumber")
        }
    }
}

sealed class EarthquakeUiState {
    object Loading : EarthquakeUiState()
    data class Success(val list: List<EarthquakeModel>) : EarthquakeUiState()
    data class Error(val message: String) : EarthquakeUiState()
}