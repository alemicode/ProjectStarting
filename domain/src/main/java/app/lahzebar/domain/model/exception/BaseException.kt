package app.lahzebar.domain.model.exception

sealed class BaseException : RuntimeException() {

    class BadRequestException(override val message: String?) : BaseException()

    class NoInternetException(override val cause: Throwable?) : BaseException()

    class ServerErrorException(val code: Int, val error: String?) : BaseException()

    class TimeoutException : BaseException()

    class UnAuthorizeException : BaseException()

    class UnexpectedResponseException(override val cause: Throwable? = null) : BaseException()

    class UnknownException(
        override val message: String?,
        override val cause: Throwable?
    ) : BaseException()
}
