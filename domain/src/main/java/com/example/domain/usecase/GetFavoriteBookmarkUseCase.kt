package com.example.domain.usecase

import com.example.domain.model.KakaoBookmark
import com.example.domain.repo.KakaoBookmarkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteBookmarkUseCase @Inject constructor(private val kakaoBookmarkRepository: KakaoBookmarkRepository) {

    operator fun invoke(): Flow<List<KakaoBookmark>> {
        return kakaoBookmarkRepository.favoriteBooks
    }

}