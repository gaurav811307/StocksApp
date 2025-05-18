package com.example.stock.ui.explore


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AssistChip
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.stock.data.model.Stock
import com.example.stock.ui.component.StockCard

@Composable
fun ExploreScreen(
    viewModel: ExploreViewModel = hiltViewModel(),
    onStockClick: (String) -> Unit,
    onViewAllClick: (String) -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        if (state.isLoading) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }

        if (state.recentlySearched.isNotEmpty()) {
            Text("Recently Searched", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8.dp))
            LazyRow {
                items(state.recentlySearched) { item ->
                    AssistChip(
                        onClick = { onStockClick(item) },
                        label = { Text(item) },
                        modifier = Modifier.padding(end = 8.dp)
                    )
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        Text("Top Gainers", style = MaterialTheme.typography.titleMedium)
        SectionGrid(state.topGainers, onStockClick, onViewAllClick, "gainers")

        Spacer(Modifier.height(16.dp))

        Text("Top Losers", style = MaterialTheme.typography.titleMedium)
        SectionGrid(state.topLosers, onStockClick, onViewAllClick, "losers")

        state.errorMessage?.let {
            Text("Error: $it", color = MaterialTheme.colorScheme.error)
        }
    }
}

@Composable
fun SectionGrid(
    stocks: List<Stock>,
    onClick: (String) -> Unit,
    onViewAllClick: (String) -> Unit,
    tag: String
) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = tag.replaceFirstChar { it.uppercase() })
        TextButton(onClick = { onViewAllClick(tag) }) { Text("View All") }
    }

    Spacer(Modifier.height(8.dp))

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.height(200.dp),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(stocks.take(4)) { stock ->
            StockCard(
                symbol = stock.symbol,
                name = stock.name,
                onClick = { onClick(stock.symbol) }
            )
        }
    }
}