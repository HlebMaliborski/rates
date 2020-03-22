package com.gangofdevelopers.currencyrates.data.common

import com.gangofdevelopers.currencyrates.common.util.Either
import com.gangofdevelopers.currencyrates.common.exception.Failure
import retrofit2.Response
import java.net.UnknownHostException

abstract class BaseDataStore {
    protected fun <T> runRequest(
        response: Response<T>
    ): Either<Failure, T> {
        return try {
            when (response.isSuccessful) {
                true -> {
                    response.body()?.let {
                        Either.Success(it)
                    } ?: Either.Error(Failure.NetworkFailure.EmptyNetworkResponse)
                }
                false -> Either.Error(
                    Failure.NetworkFailure.ServerError(
                        response.code().toString()
                    )
                )
            }
        } catch (error: Throwable) {
            Either.Error(Failure.NetworkFailure.NetworkConnection)
        }
    }
}

inline fun <R> runRequestTryCatch(action: () -> Either<Failure, R>): Either<Failure, R> {
    return try {
        action()
    } catch (error: UnknownHostException) {
        Either.Error(Failure.NetworkFailure.UnknownHostFailure)
    } catch (error: Exception) {
        Either.Error(Failure.NetworkFailure.NetworkConnection)
    }
}
