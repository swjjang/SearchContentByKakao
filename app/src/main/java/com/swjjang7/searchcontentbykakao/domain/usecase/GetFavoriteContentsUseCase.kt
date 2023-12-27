package com.swjjang7.searchcontentbykakao.domain.usecase

import com.swjjang7.searchcontentbykakao.domain.model.ApiResult
import com.swjjang7.searchcontentbykakao.domain.model.FavoriteContent
import com.swjjang7.searchcontentbykakao.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFavoriteContentsUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository,
) {
    operator fun invoke(): Flow<ApiResult<List<FavoriteContent>>> {
        return flow {
            emit(ApiResult.Success(favoriteRepository.getList()))
        }
    }
}