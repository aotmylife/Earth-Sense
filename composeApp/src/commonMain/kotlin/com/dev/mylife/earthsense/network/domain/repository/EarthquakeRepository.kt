package com.dev.mylife.earthsense.network.domain.repository

import com.dev.mylife.earthsense.network.domain.model.EarthquakeModel

interface EarthquakeRepository {
    suspend fun getLatestEarthquakes(): List<EarthquakeModel>
}