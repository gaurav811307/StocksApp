package com.example.stock.ui.viewall

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stock.data.model.Stock
import com.example.stock.data.repository.StockRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ViewAllUiState(
    val items: List<Stock> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val page: Int = 1
)

@HiltViewModel
class ViewAllViewModel @Inject constructor(
    private val repository: StockRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ViewAllUiState())
    val uiState: StateFlow<ViewAllUiState> = _uiState.asStateFlow()

    fun loadCategory(type: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            try {
                // Replace below logic with actual API integration for top gainers/losers
                val items: List<Stock> = when (type) {
                    "gainers" -> repository.getTopGainersLosers("").topGainers
                    "losers" -> repository.getTopGainersLosers("").topLosers
                    else -> emptyList()
                }

                _uiState.update {
                    it.copy(items = items, isLoading = false)
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(errorMessage = e.localizedMessage, isLoading = false)
                }
            }
        }
    }

    fun loadMore() {
        // You may simulate pagination with static or API-driven batched data.
        // Currently not used as Alpha Vantage doesn't support it directly.
    }
}