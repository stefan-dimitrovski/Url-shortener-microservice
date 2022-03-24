package com.stefan.urlshortener.service

import com.stefan.urlshortener.domain.*
import com.stefan.urlshortener.repository.ShortUrlRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.net.URL

@Service
class ShortUrlService(private val shortUrlRepository: ShortUrlRepository) {

    val logger: Logger = LoggerFactory.getLogger(ShortUrlService::class.java)

    fun getOriginalUrl(shortUrl: String): UrlResult =
        when (shortUrl.toLongOrNull()) {
            is Long -> {
                try {
                    UrlFound(shortUrlRepository.getById(shortUrl.toLong()).original_url)
                } catch (e: Exception) {
                    UrlNotFound
                }
            }
            else -> InvalidUrl
        }

    fun saveUrl(url: String): ShortUrl? =
        if (validateUrl(url)) {
            logger.info("Saving url [{}]", url)
            shortUrlRepository.save(ShortUrl(original_url = url))
        } else null

    fun validateUrl(url: String): Boolean =
        try {
            URL(url).toURI()
            true
        } catch (e: Exception) {
            logger.warn("Url parsing failed for url: [{}]", url, e)
            false
        }

}