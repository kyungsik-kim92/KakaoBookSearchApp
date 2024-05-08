package com.example.domain.usecase

import com.example.domain.model.KakaoBook
import com.example.domain.model.KakaoBookmark
import com.example.domain.repo.KakaoBookmarkRepository
import javax.inject.Inject

class DeleteBookmarkUseCase @Inject constructor(private val kakaoBookmarkRepository: KakaoBookmarkRepository) {

    suspend operator fun invoke(item: KakaoBookmark) {
        kakaoBookmarkRepository.deleteBook(item)
    }

}