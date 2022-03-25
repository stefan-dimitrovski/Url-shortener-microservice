package com.stefan.urlshortener.api

import com.stefan.urlshortener.domain.InvalidUrl
import com.stefan.urlshortener.domain.UrlFound
import com.stefan.urlshortener.domain.UrlNotFound
import com.stefan.urlshortener.service.ShortUrlService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/api/shorturl")
class ShortUrlController(val shortUrlService: ShortUrlService) {

    @GetMapping("/{shorturl}")
    fun redirectToOriginalUrl(
        @PathVariable(name = "shorturl") shortUrl: String,
        httpServletResponse: HttpServletResponse
    ): ResponseEntity<Any> =
        when (val urlResult = shortUrlService.getOriginalUrl(shortUrl)) {
            is UrlFound -> {
                httpServletResponse.setHeader("Location", urlResult.url)
                ResponseEntity(HttpStatus.MOVED_PERMANENTLY)
            }
            is UrlNotFound -> ResponseEntity(
                UrlError("Url with short url $shortUrl not found"), HttpStatus.NOT_FOUND
            )
            is InvalidUrl -> ResponseEntity.badRequest().build()
        }

    @PostMapping
    fun saveUrl(@RequestBody urlRequest: UrlRequest): ResponseEntity<Any> =
        shortUrlService.saveUrl(urlRequest.url)?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity(UrlError("invalid url"), HttpStatus.BAD_REQUEST)

}