package com.justanotherdev.copilot_app.domain.usecase

import com.justanotherdev.copilot_app.base.Result
import com.justanotherdev.copilot_app.domain.model.AuthUser
import com.justanotherdev.copilot_app.domain.repository.AuthRepository
import com.justanotherdev.copilot_app.domain.repository.UserRepository

class SignInUseCase(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository,
) {

    suspend fun execute(username: String, password: String): Result<AuthUser> {
        // Validate the input
        if (username.isBlank() || password.isBlank()) {
            return Result.Failure(Throwable("Invalid Credentials"))
        }

        // Attempt to login
        val result = authRepository.signInUserWithEmailAndPassword(username, password)

        // Handle the result
        return when (val result =
            authRepository.signInUserWithEmailAndPassword(username, password)) {
            is Result.Success -> {
                // Save the user to the local storage
//                userRepository.saveUser(result.data)
                result
            }

            is Result.Failure -> {
                result
//                when (result.error) {
//                    is NetworkError -> Result.Error(NetworkError())
//                    is InvalidCredentialsError -> Result.Error(InvalidCredentialsError())
//                    else -> Result.Error(UnknownError())
//                }
            }

        }

    }
}