package com.example.domain.usecase

import com.example.domain.model.KakaoBook
import com.example.domain.model.KakaoBookmark
import com.example.domain.repo.KakaoBookmarkRepository
import javax.inject.Inject

class GetFavoriteBookUseCase @Inject constructor(private val kakaoBookmarkRepository: KakaoBookmarkRepository) {

    operator fun invoke(): List<KakaoBookmark> {
        return kakaoBookmarkRepository.getFavoriteBooks()
    }

}