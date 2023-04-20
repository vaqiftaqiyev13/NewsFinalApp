package com.vagif_tagiyev.newsfinalapp.util

sealed class NewsResponse<T>(
    val data: T? = null,
    val message: String? = null
) {
    class SuccessResponse<T>(data: T) : NewsResponse<T>(data)
    class ErrorResponse<T>(message: String, data: T? = null) : NewsResponse<T>(data, message)
    class LoadResponse<T> : NewsResponse<T>()
}
