package com.stefan.urlshortener.domain

sealed interface UrlResult

data class UrlFound(val url: String) : UrlResult
object UrlNotFound : UrlResult
object InvalidUrl : UrlResult