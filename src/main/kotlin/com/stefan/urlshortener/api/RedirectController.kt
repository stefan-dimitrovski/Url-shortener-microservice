package com.stefan.urlshortener.api

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.view.RedirectView

@Controller
@RequestMapping("/api/redirect")
class RedirectController {

    val url = "https://www.baeldung.com/spring-redirect-and-forward"

    @GetMapping
    fun redirect(): RedirectView {
        return RedirectView(url)
    }

}