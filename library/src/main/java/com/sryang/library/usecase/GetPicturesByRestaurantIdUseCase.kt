package com.sryang.library.usecase

import com.sryang.library.data.ReviewImageEntity


interface GetPicturesByRestaurantIdUseCase {
    suspend fun invoke(restaurantId: Int) : List<ReviewImageEntity>
}