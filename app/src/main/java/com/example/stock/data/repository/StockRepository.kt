package com.example.stock.data.repository

import com.example.stock.data.api.AlphaVantageApi
import javax.inject.Inject

class StockRepository@Inject constructor(
    private val api: AlphaVantageApi
) {
    suspend fun getTopGainersLosers(keyword: String) = api.getTopGainersLosers(keyword)
    suspend fun getCompanyOverview(symbol: String) = api.getCompanyOverview(symbol)
    suspend fun getTimeSeriesDaily(symbol: String) = api.getDailyTimeSeries(symbol)
}