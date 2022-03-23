package com.stefan.urlshortener.api

import com.stefan.urlshortener.domain.ShortUrl
import com.stefan.urlshortener.service.ShortUrlService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.view.RedirectView

@RestController
@RequestMapping("/api/shorturl")
class ShortUrlController(val shortUrlService: ShortUrlService) {

    @GetMapping
    fun getAllUrls(): List<ShortUrl> {
        return shortUrlService.getAllUrls()
    }

    @GetMapping("/{shorturl}")
    fun redirectToOriginalUrl(@PathVariable(name = "shorturl") shortUrl: String): RedirectView {
        val url = shortUrlService.getOriginalUrl(shortUrl)
        return RedirectView(url)
    }

    @PostMapping
    fun insertUrl(@RequestBody urlRequest: UrlRequest) = shortUrlService.insertUrl(urlRequest.url)
}