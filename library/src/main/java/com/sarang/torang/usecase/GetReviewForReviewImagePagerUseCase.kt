package com.sarang.torang.usecase

import com.sarang.torang.uistate.ImagePagerUiState


interface GetReviewForReviewImagePagerUseCase {
    suspend fun invoke(reviewId : Int) : ImagePagerUiState
}