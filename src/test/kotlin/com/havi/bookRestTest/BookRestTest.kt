package com.havi.bookRestTest

import com.havi.service.BookRestService
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.jupiter.api.Test
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
        // FIXME
    }

    @Test
    fun restErrorTest() {
        // FIXME
        // Note: this might not work
    }
}
