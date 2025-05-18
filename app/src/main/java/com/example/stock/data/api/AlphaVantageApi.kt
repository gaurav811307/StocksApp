package com.example.stock.data.api

import com.example.stock.data.model.StockDetail
import com.example.stock.data.model.TimeSeriesDaily
import com.example.stock.data.model.TopGainersLosers
import retrofit2.http.GET
import retrofit2.http.Query

interface AlphaVantageApi {

    @GET("query?function=TOP_GAINERS_LOSERS&apikey=demo")
    suspend fun getTopGainersLosers(
        @Query("keywords") keyword: String,
        @Query("apikey") apiKey: String = com.example.stock.utils.Constants.API_KEY
    ): TopGainersLosers

    @GET("query?function=OVERVIEW")
    suspend fun getCompanyOverview(
        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String = com.example.stock.utils.Constants.API_KEY
    ): StockDetail

    @GET("query?function=TIME_SERIES_DAILY")
    suspend fun getDailyTimeSeries(
        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String = com.example.stock.utils.Constants.API_KEY
    ): TimeSeriesDaily

}