package com.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.web.servlet.LocaleResolver
import org.springframework.web.servlet.i18n.SessionLocaleResolver
import java.util.*


@SpringBootApplication
class DemoApplication {
    @Bean
    fun localeResolver(): LocaleResolver {
        val resolver = SessionLocaleResolver()
        resolver.setDefaultLocale(Locale.US)
        return resolver
    }

    @Bean
    fun bundleResourceSource(): ResourceBundleMessageSource {
        val bundleResourceSource = ResourceBundleMessageSource()
        bundleResourceSource.setBasename("messages")
        return bundleResourceSource
    }
}

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}


