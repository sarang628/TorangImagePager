package com.sarang.torang.usecase

import com.sarang.torang.data.data.ReviewImageEntity


interface GetPicturesByRestaurantIdUseCase {
    suspend fun invoke(restaurantId: Int) : List<ReviewImageEntity>
}