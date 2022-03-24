package com.stefan.urlshortener.service

import com.stefan.urlshortener.repository.ShortUrlRepository
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.mockito.Mockito.mock

internal class ShortUrlServiceTest {

    private val mockUrlRepository: ShortUrlRepository = mock(ShortUrlRepository::class.java)
    private val shortUrlService: ShortUrlService = ShortUrlService(mockUrlRepository)

    @Test
    fun `test successful url validation`() {

    }

    @Test
    fun `test failed url validation`() {

    }

    @Test
    fun `test successful url save`() {

    }

    @Test
    fun `test failed url save`() {

    }

    @Test
    fun `test successful url retrieved`() {

    }

    @Test
    fun `test url not found`() {

    }

    @Test
    fun `test invalid url entered`() {

    }

}