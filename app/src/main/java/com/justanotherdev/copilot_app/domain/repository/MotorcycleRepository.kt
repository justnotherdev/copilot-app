package com.justanotherdev.copilot_app.domain.repository

import com.justanotherdev.copilot_app.base.Result
import com.justanotherdev.copilot_app.data.model.NetworkMotorcycle
import com.justanotherdev.copilot_app.domain.model.Motorcycle

internal interface MotorcycleRepository {
//    suspend fun getAll(): Result<List<Motorcycle>>
//    suspend fun getById(id: String): Result<Motorcycle>
//    suspend fun getByUserId(userId: String): Result<List<Motorcycle>>
    suspend fun getActiveByOwnerId(ownerId: String): Result<Motorcycle>
}