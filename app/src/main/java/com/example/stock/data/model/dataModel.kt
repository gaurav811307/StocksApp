package com.example.stock.data.model

data class Stock(
    val symbol: String,
    val name: String,
    val price: Double,
    val change: Double,
    val changePercent: Double,
    val exchange: String = "",
    val type: String = "STOCK"
)
data class StockDetail(
    val symbol: String,
    val name: String,
    val description: String?,
    val price: Double,
    val change: Double,
    val changePercent: Double,
    val marketCap: String?,
    val peRatio: String?,
    val sector: String?,
    val industry: String?,
    val exchange: String?
)

data class TopGainersLosers(
    val topGainers: List<Stock>,
    val topLosers: List<Stock>
)

data class StockSearch(
    val symbol: String,
    val name: String,
    val type: String,
    val region: String,
    val marketOpen: String,
    val marketClose: String,
    val timezone: String,
    val currency: String,
    val matchScore: String
)

data class SearchResult(
    val bestMatches: List<Map<StockSearch, String>>
)

data class TimeSeriesDaily(
    val metaData: Map<String, String>,
    val timeSeriesDaily: Map<String, Map<String, String>>
)


data class CompanyOverview(
    val Symbol: String,
    val AssetType: String,
    val Name: String,
    val Description: String,
    val Sector: String,
    val Industry: String,
    val MarketCapitalization: String
)