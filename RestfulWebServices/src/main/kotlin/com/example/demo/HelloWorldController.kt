package com.example.demo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class HelloWorldController(@Autowired val bundleResourceMessage: ResourceBundleMessageSource) {
    @GetMapping("/hello")
    fun helloWorldInternationalized(@RequestHeader("Accept-Language", required = false) locale: Locale = Locale.US): String {
        return bundleResourceMessage.getMessage("good.morning.message", null, locale)
    }
}
