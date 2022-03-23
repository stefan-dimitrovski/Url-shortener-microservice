package com.stefan.urlshortener.domain

import javax.persistence.*

@Entity
@Table(name = "short_urls")
data class ShortUrl(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val short_url: Long? = null,
    @Column
    val original_url: String,
)