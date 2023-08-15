package com.justanotherdev.copilot_app.domain.repository

import com.justanotherdev.copilot_app.base.Result
import com.justanotherdev.copilot_app.domain.model.AuthUser

interface AuthRepository {
    fun signInUserWithEmailAndPassword(username: String, password: String): Result<AuthUser>
}