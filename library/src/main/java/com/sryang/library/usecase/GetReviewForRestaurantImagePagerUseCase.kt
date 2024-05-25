package com.sryang.library.usecase

import com.sryang.library.data.RestaurantImagePageContents

interface GetReviewForRestaurantImagePagerUseCase {
    suspend fun invoke(reviewId : Int) : RestaurantImagePageContents
}