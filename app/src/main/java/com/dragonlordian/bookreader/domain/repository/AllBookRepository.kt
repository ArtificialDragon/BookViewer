package com.dragonlordian.bookreader.domain.repository

import com.dragonlordian.bookreader.data.model.BookCategory
import com.dragonlordian.bookreader.data.model.BookModel
import com.dragonlordian.bookreader.data.model.ResultState
import kotlinx.coroutines.flow.Flow

interface AllBookRepository {

    fun getAllBooks(): Flow<ResultState<List<BookModel>>>

    fun getAllCategory(): Flow<ResultState<List<BookCategory>>>

    fun getAllBooksByCategory(category: String): Flow<ResultState<List<BookModel>>>
}