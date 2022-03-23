package com.stefan.urlshortener.repository

import com.stefan.urlshortener.domain.ShortUrl
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ShortUrlRepository : JpaRepository<ShortUrl, Long>