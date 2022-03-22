package com.stefan.urlshortener

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class UrlShortenerMicroserviceApplication

fun main(args: Array<String>) {
	runApplication<UrlShortenerMicroserviceApplication>(*args)
}
