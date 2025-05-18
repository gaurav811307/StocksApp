package com.example.stock.ui.product


import android.graphics.Color
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.stock.ui.component.LineChart
import com.example.stock.data.model.CompanyOverview

@Composable
fun ProductScreen(
    symbol: String,
    viewModel: ProductViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(symbol) {
        viewModel.loadStock(symbol)
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        if (state.isLoading) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }

        state.errorMessage?.let {
            Text(text = it, color = MaterialTheme.colorScheme.error)
        }

        state.companyOverview?.let { info ->
            CompanyInfo(info)
            Spacer(modifier = Modifier.height(16.dp))
        }

        if (state.dailyData.isNotEmpty()) {
            Text("Price (Last 30 Days)", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8.dp))
            LineChart(data = state.dailyData.values.toList())
        }
    }
}

@Composable
fun CompanyInfo(info: CompanyOverview) {
    Column {
        Text(text = info.Name, style = MaterialTheme.typography.headlineSmall)
        Text(text = info.Sector, style = MaterialTheme.typography.bodySmall)
        Text(text = "Industry: ${info.Industry}")
        Spacer(Modifier.height(8.dp))
        Text(text = info.Description.take(300) + "...")
        Spacer(Modifier.height(8.dp))
        Text(text = "Market Cap: ${info.MarketCapitalization}")
    }
}
