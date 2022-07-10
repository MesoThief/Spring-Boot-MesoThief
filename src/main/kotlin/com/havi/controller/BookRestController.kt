package com.havi.controller

import com.havi.domain.Book
import com.havi.service.BookRestService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class BookRestController(
    private val bookRestService: BookRestService,
) {
    @GetMapping(path = ["/rest/test"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getRestBooks(): Book {
        return bookRestService.getRestBook()
    }
}
