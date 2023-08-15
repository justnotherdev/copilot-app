package com.justanotherdev.copilot_app.domain.usecase

import com.justanotherdev.copilot_app.domain.model.Motorcycle
import com.justanotherdev.copilot_app.base.Result
import com.justanotherdev.copilot_app.domain.repository.MotorcycleRepository

internal class GetMotorcycleOverviewUseCase(
    private val motorcycleRepository: MotorcycleRepository
) {
    suspend operator fun invoke(ownerId: String): Result<Motorcycle> =
        motorcycleRepository.getActiveByOwnerId(ownerId)
}