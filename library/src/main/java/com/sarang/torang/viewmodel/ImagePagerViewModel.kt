package com.sarang.torang.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sarang.torang.uistate.ImagePagerUiState
import com.sarang.torang.usecase.GetReviewForReviewImagePagerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImagePagerViewModel @Inject constructor(
    private val getReviewUseCase: GetReviewForReviewImagePagerUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(ImagePagerUiState())
    val uiState: StateFlow<ImagePagerUiState> = _uiState

    fun load(reviewId: Int) {
        Log.d("__ImagePagerViewModel", "load: $reviewId")
        viewModelScope.launch {
            try {
                _uiState.emit(getReviewUseCase.invoke(reviewId))
            } catch (e: Exception) {
                Log.e("__ImagePagerViewModel", "load: ${e.message}")
            }
        }
    }
}