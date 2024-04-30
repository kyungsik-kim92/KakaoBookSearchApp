package com.example.domain.usecasse

import com.example.domain.repo.KakaoSearchRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetKakaoSearchBooksUseCase @Inject constructor(private val kakaoSearchRepository: KakaoSearchRepository) {
    operator fun invoke(
        query: String,
        sort: String,
        page: Int,
        size: Int,
    ) = flow { emit(kakaoSearchRepository.searchBooks(query, sort, page, size)) }

}