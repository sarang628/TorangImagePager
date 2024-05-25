package com.sryang.library.uistate

import com.sryang.library.data.ReviewImageEntity

data class RestaurantImagePagerUiState(
    val list: List<ReviewImageEntity> = listOf(),
    val position: Int = 0,
    val contents: String = "",
    val likeCount : String = "",
    val name : String = "",
    val commentCount : String = "",
)

val RestaurantImagePagerUiState.pictures: List<String>
    get() = this.list.map { it.pictureUrl }