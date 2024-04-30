package com.example.domain.usecasse

import com.example.domain.repo.SearchRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class GetKakaoSearchBooksUseCase(private val kakaRepository: SearchRepository) {

    operator fun invoke(
        query: String,
        sort: String,
        page: Int,
        size: Int,
    ) = flow { emit(kakaRepository.searchBooks(query, sort, page, size)) }


}