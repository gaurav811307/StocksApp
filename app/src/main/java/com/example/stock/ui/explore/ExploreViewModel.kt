package com.example.stock.ui.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stock.data.model.Stock
import com.example.stock.data.repository.StockRepository
import com.example.stock.utils.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


data class ExploreUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val recentlySearched: List<String> = emptyList(),
    val topGainers: List<Stock> = emptyList(),
    val topLosers: List<Stock> = emptyList()
)

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val repository: StockRepository,
    private val dataStore: DataStoreManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(ExploreUiState())
    val uiState: StateFlow<ExploreUiState> = _uiState.asStateFlow()

    init {
        loadRecentlySearched()
        loadTopGainersAndLosers()
    }

    private fun loadRecentlySearched() {
        viewModelScope.launch {
            dataStore.getRecentSearches().collect { recent ->
                _uiState.update { it.copy(recentlySearched = recent) }
            }
        }
    }

    private fun loadTopGainersAndLosers() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            try {
                // Placeholder: Call your API here and parse data
                // val gainers = repository.getTopGainers()
                // val losers = repository.getTopLosers()

                val gainers = emptyList<Stock>() // replace with API response
                val losers = emptyList<Stock>()

                _uiState.update {
                    it.copy(
                        topGainers = gainers,
                        topLosers = losers,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        errorMessage = e.localizedMessage ?: "Unknown error",
                        isLoading = false
                    )
                }
            }
        }
    }
}
