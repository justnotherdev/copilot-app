package com.justanotherdev.copilot_app.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.justanotherdev.copilot_app.base.Result
import com.justanotherdev.copilot_app.data.model.NetworkMotorcycle
import com.justanotherdev.copilot_app.domain.model.Motorcycle
import com.justanotherdev.copilot_app.domain.repository.MotorcycleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.io.IOException
import java.lang.Exception

internal class MotorcycleRepositoryImpl(
    private val mapMotorcycleDto: (NetworkMotorcycle) -> Motorcycle,
) : MotorcycleRepository {

    private var db = FirebaseFirestore.getInstance()

    override suspend fun getActiveByOwnerId(ownerId: String): Result<Motorcycle> {
        return try {
            withContext(Dispatchers.IO) {
                val collection = db.collection("vehicles")
                val snapshot = collection.whereEqualTo("owner_d", ownerId).get().await().single()
                val motorcycle = mapMotorcycleDto(snapshot.toObject(NetworkMotorcycle::class.java))
                println(motorcycle)
                Result.Success(motorcycle)
            }

        } catch (ex: Exception) {
            Result.Failure(ex)
        }

    }
}