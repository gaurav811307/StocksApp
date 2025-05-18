package com.example.stock.ui.product


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stock.data.model.CompanyOverview
import com.example.stock.data.model.StockDetail
import com.example.stock.data.repository.StockRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ProductUiState(
    val companyOverview: CompanyOverview? = null,
    val dailyData: Map<String, Float> = emptyMap(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: StockRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductUiState())
    val uiState: StateFlow<ProductUiState> = _uiState.asStateFlow()

    fun loadStock(symbol: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            try {
                val overview = repository.getCompanyOverview(symbol)
                val priceResponse = repository.getTimeSeriesDaily(symbol)

                 // Parse latest 30 days
//                val data: Map<String, Float> = priceResponse.timeSeriesDaily
//                    .entries
//                    .take(30)
//                    .map { it.key to it.value.close.toFloat() }
//                    .reversed()
//                    .toMap()
//
//                _uiState.update {
//                    it.copy(
//                        companyOverview = overview,
//                        dailyData = data,
//                        isLoading = false
//                    )
//                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(isLoading = false, errorMessage = e.localizedMessage ?: "Error")
                }
            }
        }
    }
}
