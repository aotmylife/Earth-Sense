package com.dev.mylife.earthsense.network.response

import kotlinx.serialization.Serializable

@Serializable
data class EarthquakeResponse(
    val features: List<EarthquakeFeature>
)

@Serializable
data class EarthquakeFeature(
    val id: String,
    val properties: EarthquakeProps
)

@Serializable
data class EarthquakeProps(
    val mag: Double,
    val place: String,
    val time: Long
)
