package com.dev.mylife.earthsense.network.domain.model

data class EarthquakeModel(
    val id: String,
    val magnitude: Double,
    val place: String,
    val time: Long
)