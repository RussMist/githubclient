package com.russmsit.githubclient.domain.enteties

/**
 * TODO
 */
sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Failure(val message: String, val cause: Throwable? = null) : Result<Nothing>()
}

inline fun <T> Result<T>.onSuccess(action: (value: T) -> Unit): Result<T> {
    if (this is Result.Success<T>) action(this.data)
    return this
}

inline fun <T> Result<T>.onFailure(action: (message: String, cause: Throwable?) -> Unit): Result<T> {
    if (this is Result.Failure) action(this.message, this.cause)
    return this
}

val <T> Result<T>.isSuccess get() = this is Result.Success<T>

fun <T> Result<T>.asSuccess(): Result.Success<T> = this as Result.Success<T>

fun <T> Result<T>.asFailure(): Result.Failure = this as Result.Failure

fun <T> Result<T>.takeIfSuccess(): Result.Success<T>? = this.takeIf { isSuccess }?.asSuccess()

fun <T> Result<T>.takeIfFailure(): Result.Failure? = this.takeIf { !isSuccess }?.asFailure()