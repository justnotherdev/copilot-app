package com.justanotherdev.copilot_app.data.mapper

import com.justanotherdev.copilot_app.data.model.NetworkMotorcycle
import com.justanotherdev.copilot_app.domain.model.Motorcycle

internal object MotorcycleMapper {
    fun map(networkMotorcycle: NetworkMotorcycle): Motorcycle {
        return Motorcycle(
            id = networkMotorcycle.id,
            codename = networkMotorcycle.codename.orEmpty(),
            status = networkMotorcycle.status.orEmpty(),
            brand = networkMotorcycle.modelId.orEmpty(),
            imagePath = networkMotorcycle.imagePath.orEmpty()
        )
    }

}