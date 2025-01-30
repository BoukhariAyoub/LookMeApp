package com.lookmyup.app.domain.entities

sealed class AppError : Throwable() {

    data class NetworkError(override val message: String?) : AppError()

    data class ServerError(val code: Int, override val message: String?) : AppError()

    data class ValidationError(val field: String, val reason: String) : AppError()

    data class UnexpectedError(override val cause: Throwable?) : AppError()
}