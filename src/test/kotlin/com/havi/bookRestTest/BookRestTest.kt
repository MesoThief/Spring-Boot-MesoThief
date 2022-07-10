package com.havi.bookRestTest

import com.havi.service.BookRestService
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest
import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.client.MockRestServiceServer
import org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo
import org.springframework.test.web.client.response.MockRestResponseCreators.withServerError
import org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.client.HttpServerErrorException.InternalServerError

@RunWith(SpringRunner::class)
@RestClientTest(BookRestService::class)
class BookRestTest {
    @Rule
    var thrown: ExpectedException = ExpectedException.none()

    @Autowired
    private lateinit var bookRestService: BookRestService

    @Autowired
    private lateinit var server: MockRestServiceServer

    @Test
    fun restTest() {
        server.expect(requestTo("/rest/test"))
            .andRespond(withSuccess(ClassPathResource("/test.json", javaClass), MediaType.APPLICATION_JSON))
        val book = bookRestService.getRestBook()
        assertThat(book.title).isEqualTo("테스트")
    }

    @Test
    fun restErrorTest() {
        server.expect(requestTo("/rest/test"))
            .andRespond(withServerError())
        // thrown.expect(InternalServerError::class.java)
        // bookRestService.getRestBook()

        assertThrows<HttpServerErrorException> { bookRestService.getRestBook() }
        // 성...공?
    }
}
