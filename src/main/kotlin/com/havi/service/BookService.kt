package com.havi.service

import com.havi.domain.Book

interface BookService {
    fun getBookList(): List<Book>
}
