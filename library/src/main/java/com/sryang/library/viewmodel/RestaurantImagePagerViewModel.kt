package com.sryang.library.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sryang.library.uistate.RestaurantImagePagerUiState
import com.sryang.library.usecase.GetPicturesByRestaurantIdUseCase
import com.sryang.library.usecase.GetReviewForRestaurantImagePagerUseCase
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
        Log.d("__RestaurantImagePagerViewModel", "load imageId: $imageId")
        viewModelScope.launch {
            val list = getPicturesUseCase.invoke(imageId)
            var position = 0
            list.find { it.pictureId == imageId }?.let {
                position = list.indexOf(it)
            }
            _uiState.update {
                it.copy(
                    list = list,
                    position = position
                )
            }
        }
    }

    fun onPage(position: Int) {
        Log.d(
            "__RestaurantImagePagerViewModel",
            "onPage: $position ReviewImageEntity : ${_uiState.value.list[position]}"
        )

        viewModelScope.launch {
            val result = getReviewUseCase.invoke(_uiState.value.list[position].reviewId)
            _uiState.update {
                it.copy(contents = result.contents,
                    likeCount = result.likeCount,
                    commentCount = result.commentCount,
                    name = result.name)
            }
            Log.d("__RestaurantImagePagerViewModel", "ReviewAndImageEntity: $result")
        }
    }
}