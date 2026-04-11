package com.sarang.torang.usecase

import com.sarang.torang.data.data.RestaurantImagePageContents

interface GetReviewForRestaurantImagePagerUseCase {
    suspend fun invoke(reviewId : Int) : RestaurantImagePageContents
}