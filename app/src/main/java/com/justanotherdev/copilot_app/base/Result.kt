package com.justanotherdev.copilot_app.base

sealed class Result<out T> { // A wrapper for handling failing requests
    data class Success<out T>(val data: T) : Result<T>()
    data class Failure<out T>(val error: Throwable) : Result<T>()

    inline fun <C> fold(success: (T) -> C, failure: (Throwable) -> C): C = when (this) {
        is Success -> success(data)
        is Failure -> failure(error)
    }

}