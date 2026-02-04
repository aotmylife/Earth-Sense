package com.dev.mylife.earthsense.network.domain.repository

import com.dev.mylife.earthsense.network.EarthquakeApi
import com.dev.mylife.earthsense.network.domain.model.EarthquakeModel

class EarthquakeRepositoryImpl(
    private val api: EarthquakeApi
) : EarthquakeRepository {
    override suspend fun getLatestEarthquakes(): List<EarthquakeModel> {
        return api.getLatestEarthquakes().features.map { feature ->
            EarthquakeModel(
                id = feature.id,
                magnitude = feature.properties.mag,
                place = feature.properties.place,
                time = feature.properties.time
            )
        }
    }
}