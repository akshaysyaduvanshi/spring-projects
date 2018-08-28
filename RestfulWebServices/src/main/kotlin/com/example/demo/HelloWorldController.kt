package com.example.demo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloWorldController(@Autowired val bundleResourceMessage: ResourceBundleMessageSource) {
    @GetMapping("/hello")
    fun helloWorldInternationalized(): String {
        return bundleResourceMessage.getMessage("good.morning.message", null, LocaleContextHolder.getLocale())
    }
}
