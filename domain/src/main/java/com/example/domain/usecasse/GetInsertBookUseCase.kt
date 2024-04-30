package com.example.domain.usecasse

import com.example.domain.model.KakaoBookmark
import com.example.domain.repo.KakaoBookmarkRepository
import javax.inject.Inject

class GetInsertBookUseCase @Inject constructor(private val kakaoBookmarkRepository: KakaoBookmarkRepository) {

    suspend operator fun invoke(item: KakaoBookmark) {
        kakaoBookmarkRepository.insertBook(item)
    }


}