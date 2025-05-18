package com.example.stock.ui.viewall

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.stock.ui.component.StockCard

@Composable
fun ViewAllScreen(
    type: String,
    onStockClick: (String) -> Unit,
    viewModel: ViewAllViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(type) {
        viewModel.loadCategory(type)
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = when (type) {
                "gainers" -> "Top Gainers"
                "losers" -> "Top Losers"
                else -> "Stocks"
            },
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(Modifier.height(16.dp))

        if (state.isLoading) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }

        state.errorMessage?.let {
            Text("Error: $it", color = MaterialTheme.colorScheme.error)
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(bottom = 32.dp)
        ) {
            items(state.items) { stock ->
                StockCard(
                    symbol = stock.symbol,
                    name = stock.name,
                    onClick = { onStockClick(stock.symbol) }
                )
            }
        }
    }
}