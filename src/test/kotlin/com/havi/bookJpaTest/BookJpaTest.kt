package com.havi.bookJpaTest

import com.havi.domain.Book
import com.havi.repository.BookRepository
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.contains
import org.hamcrest.Matchers.hasSize
import org.hamcrest.Matchers.`is`
import org.hamcrest.collection.IsEmptyCollection
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDateTime

@RunWith(SpringRunner::class)
@DataJpaTest
class BookJpaTest {
    companion object {
        const val BOOT_TEST_TITLE = "Spring Boot Test Book"
    }

    @Autowired
    private lateinit var testEntityManager: TestEntityManager

    @Autowired
    private lateinit var bookRepository: BookRepository

    @Test
    fun bookSaveTest() {
        // FIXME
    }

    @Test
    fun bookListSaveAndSearchTest() {
        // FIXME
    }

    @Test
    fun bookListSaveAndDeleteTest() {
        // FIXME
    }
}
