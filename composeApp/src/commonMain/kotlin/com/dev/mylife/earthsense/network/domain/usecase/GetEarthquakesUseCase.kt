package com.dev.mylife.earthsense.network.domain.usecase

import com.dev.mylife.earthsense.network.domain.model.EarthquakeModel
import com.dev.mylife.earthsense.network.domain.repository.EarthquakeRepository

class GetEarthquakesUseCase(private val repository: EarthquakeRepository) {

    suspend operator fun invoke(): Result<List<EarthquakeModel>> {
        return try {
            val list = repository.getLatestEarthquakes()

            val processedList = list.filter { it.magnitude > 4 }
                .sortedByDescending { it.time }

            Result.success(processedList)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}