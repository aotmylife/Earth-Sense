package com.dev.mylife.earthsense.network

import com.dev.mylife.earthsense.network.response.EarthquakeResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import io.ktor.client.request.*
import kotlinx.datetime.*
import kotlinx.serialization.json.Json
import kotlin.time.Clock

class EarthquakeApi {
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
            })
        }
    }

    suspend fun getLatestEarthquakes(): EarthquakeResponse {
        val now = Clock.System.now()
        val today = now.toLocalDateTime(TimeZone.UTC).date

        val yesterday = now.minus(1, DateTimeUnit.DAY, TimeZone.UTC)
            .toLocalDateTime(TimeZone.UTC).date

        return httpClient.get("https://earthquake.usgs.gov/fdsnws/event/1/query") {
            parameter("format", "geojson")
            parameter("starttime", yesterday.toString())
            parameter("endtime", today.toString())
            parameter("orderby", "time")
        }.body()
    }
}