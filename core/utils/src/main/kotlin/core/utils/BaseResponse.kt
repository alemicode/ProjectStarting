package core.utils

data class BaseResponse<T>(
    val httpStatusCode: Int,
    val httpStatusMessage: String,
    val statusCode: Int = httpStatusCode,
    val statusMessage: String = httpStatusMessage,
    val data: T?
)
