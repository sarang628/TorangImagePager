package com.sryang.library.usecase

import com.sryang.library.uistate.ImagePagerUiState


interface GetReviewForReviewImagePagerUseCase {
    suspend fun invoke(reviewId : Int) : ImagePagerUiState
}