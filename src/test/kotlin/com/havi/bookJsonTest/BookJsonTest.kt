package com.havi.bookJsonTest

import com.havi.domain.Book
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.json.JsonTest
import org.springframework.boot.test.json.JacksonTester
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@JsonTest
class BookJsonTest {
    @Autowired
    private lateinit var json: JacksonTester<Book>

    @Test
    @Throws(Exception::class)
    fun jsonTest() {
        // FIXME
    }
}
