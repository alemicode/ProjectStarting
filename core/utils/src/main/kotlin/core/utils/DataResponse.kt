package core.utils

data class DataResponse<T>(
    val results: List<T>,
    val count: Int = results.size,
    val total: Int = results.size,
    val limit: Int = results.size,
    val offset: Int = count - results.size,
    val page: Int = if (limit == 0) 0 else offset / limit
)
