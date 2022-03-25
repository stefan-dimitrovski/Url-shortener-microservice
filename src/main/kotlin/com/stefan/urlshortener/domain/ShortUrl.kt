package com.stefan.urlshortener.domain

import javax.persistence.*

@Entity
@Table(name = "short_urls")
data class ShortUrl(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "short_url")
    val shortUrl: Long? = null,
    @Column(name = "original_url")
    val originalUrl: String,
)