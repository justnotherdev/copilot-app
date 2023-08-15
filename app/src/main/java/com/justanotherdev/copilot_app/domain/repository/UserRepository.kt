package com.justanotherdev.copilot_app.domain.repository

import com.justanotherdev.copilot_app.base.Result
import com.justanotherdev.copilot_app.domain.model.User

interface UserRepository {
    fun getUserById(id: String): Result<User>
}