package com.sarang.torang.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sarang.torang.uistate.RestaurantImagePagerUiState
import com.sarang.torang.usecase.GetPicturesByRestaurantIdUseCase
import com.sarang.torang.usecase.GetReviewForRestaurantImagePagerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantImagePagerViewModel @Inject constructor(
    private val getPicturesUseCase: GetPicturesByRestaurantIdUseCase,
    private val getReviewUseCase: GetReviewForRestaurantImagePagerUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(RestaurantImagePagerUiState())
    val uiState: StateFlow<RestaurantImagePagerUiState> = _uiState
    fun load(imageId: Int) {
        viewModelScope.launch {
            val list = getPicturesUseCase.invoke(imageId)
            val index = list.map { it.pictureId }
                            .indexOfFirst { it == imageId }
            _uiState.update { it.copy(list      = list,
                                      position  = 0.coerceAtLeast(index))
            }
        }
    }

    fun onPage(position: Int) {
        viewModelScope.launch {
            val result = getReviewUseCase.invoke(_uiState.value.list[position].reviewId)
            _uiState.update {
                it.copy(contents     = result.contents,
                        likeCount    = result.likeCount,
                        commentCount = result.commentCount,
                        name         = result.name,
                        reviewId     = result.reviewId,
                        userId       = result.userId)
            }
        }
    }
}