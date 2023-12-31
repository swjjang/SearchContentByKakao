package com.swjjang7.searchcontentbykakao.domain.usecase

import com.swjjang7.searchcontentbykakao.domain.model.FavoriteContent
import com.swjjang7.searchcontentbykakao.domain.repository.FavoriteRepository
import javax.inject.Inject

class RemoveFavoriteContentsUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository,
) {
    suspend operator fun invoke(favoriteContent: FavoriteContent) {
        favoriteRepository.remove(favoriteContent)
    }
}