package com.stefan.urlshortener.service

import com.stefan.urlshortener.domain.InvalidUrl
import com.stefan.urlshortener.domain.ShortUrl
import com.stefan.urlshortener.domain.UrlFound
import com.stefan.urlshortener.domain.UrlNotFound
import com.stefan.urlshortener.repository.ShortUrlRepository
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.mockito.Mockito
import org.mockito.Mockito.mock

internal class ShortUrlServiceTest {

    private val mockUrlRepository: ShortUrlRepository = mock(ShortUrlRepository::class.java)
    private val shortUrlService: ShortUrlService = ShortUrlService(mockUrlRepository)

    @Test
    fun `test successful url validation`() {
        val url = "https://google.com"

        val result = shortUrlService.validateUrl(url)

        assertEquals(true, result)
    }

    @Test
    fun `test failed url validation`() {
        val url = "invalid url"

        val result = shortUrlService.validateUrl(url)

        assertEquals(false, result)
    }

    @Test
    fun `test successful url save`() {
        val url = "https://google.com"
        val id = 1L
        Mockito.`when`(mockUrlRepository.save(ShortUrl(null, url))).thenReturn(ShortUrl(id, url))

        val result = shortUrlService.saveUrl(url)

        assertEquals(ShortUrl(id, url), result)
    }

    @Test
    fun `test failed url save`() {
        val url = "https://google.com"
        val id = 1L
        Mockito.`when`(mockUrlRepository.save(ShortUrl(null, url))).thenReturn(ShortUrl(id, url))

        val result = shortUrlService.saveUrl(url)

        assertEquals(ShortUrl(id, url), result)
    }

    @Test
    fun `test save url return null`() {
        val url = "invalid url"

        val result = shortUrlService.saveUrl(url)

        assertEquals(null, result)
    }

    @Test
    fun `duplicate url not saved`() {
        val url = "https://google.com"
        val id = 1L
        Mockito.`when`(mockUrlRepository.findByOriginalUrl(url)).thenReturn(ShortUrl(id, url))

        val result = mockUrlRepository.findByOriginalUrl(url)

        assertEquals(ShortUrl(id, url), result)
    }

    @Test
    fun `test successful url retrieved`() {
        val url = "https://google.com"
        val id = 1L
        Mockito.`when`(mockUrlRepository.getById(id)).thenReturn(ShortUrl(id, url))

        val urlResult = shortUrlService.getOriginalUrl(id.toString())

        assertEquals(UrlFound(url), urlResult)
    }

    @Test
    fun `test url not found`() {
        val id = 1L
        Mockito.`when`(mockUrlRepository.getById(id)).thenThrow(RuntimeException())

        val urlResult = shortUrlService.getOriginalUrl(id.toString())

        assertEquals(UrlNotFound, urlResult)
    }

    @Test
    fun `test invalid url entered`() {
        val url = "invalid url"

        val urlResult = shortUrlService.getOriginalUrl(url)

        assertEquals(InvalidUrl, urlResult)
    }

}