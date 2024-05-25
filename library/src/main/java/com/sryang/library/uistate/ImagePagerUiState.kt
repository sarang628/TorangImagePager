package com.sryang.library.uistate

data class ImagePagerUiState(
    val list: List<String> = listOf<String>(),
    val date: String = "",
    val likeCount: String = "",
    val name: String = "",
    val contents: String = "",
    val commentCount: String = "",
)