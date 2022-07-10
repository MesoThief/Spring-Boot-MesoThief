package com.havi.service

import com.havi.domain.Book
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject

@Service
class BookRestService(
    restTemplateBuilder: RestTemplateBuilder,
) {
    private val restTemplate: RestTemplate = restTemplateBuilder.rootUri("/rest/test").build()

    fun getRestBook(): Book {
        return this.restTemplate.getForObject("/rest/test", Book::class)
    }
}
