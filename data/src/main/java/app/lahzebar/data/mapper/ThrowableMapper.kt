package app.lahzebar.data.mapper

import app.lahzebar.data.remote.response.ExceptionResponse
import app.lahzebar.domain.model.exception.BaseException
import com.google.gson.Gson
import com.google.gson.JsonParseException
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

internal fun Throwable.toDomain(gson: Gson): BaseException = when (this) {
    is BaseException -> this
    is HttpException -> handleHttpExceptions(this, gson)
    is JsonParseException -> BaseException.UnexpectedResponseException(cause)
    is IOException -> BaseException.NoInternetException(cause)
    else -> BaseException.UnknownException(message, cause)
}

private fun handleHttpExceptions(ex: HttpException, gson: Gson): BaseException = when (ex.code()) {
    401 -> BaseException.UnAuthorizeException()
    400 -> {
        val exceptionResponse = ex.response()?.errorBody()?.string()?.let {
            try {
                gson.fromJson(it, ExceptionResponse::class.java)
            } catch (ex: JsonParseException) {
                return BaseException.UnexpectedResponseException(ex.cause)
            }
        }
        exceptionResponse?.let {
            BaseException.BadRequestException(it.message)
        } ?: BaseException.UnexpectedResponseException(ex.cause)
    }
    else -> BaseException.ServerErrorException(ex.code(), ex.message)
}
