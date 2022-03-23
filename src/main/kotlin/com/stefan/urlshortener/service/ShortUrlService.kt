package com.stefan.urlshortener.service

import com.stefan.urlshortener.domain.ShortUrl
import com.stefan.urlshortener.repository.ShortUrlRepository
import org.springframework.stereotype.Service

@Service
class ShortUrlService(private val shortUrlRepository: ShortUrlRepository) {

    fun getOriginalUrl(shortUrl: String): String =
        shortUrlRepository.getById(shortUrl.toLong()).original_url

    fun insertUrl(url: String): ShortUrl = shortUrlRepository.save(ShortUrl(original_url = url))

    fun getAllUrls(): List<ShortUrl> {
        return shortUrlRepository.findAll()
    }

}