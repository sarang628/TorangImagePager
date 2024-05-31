package com.sryang.library.uistate

data class ImagePagerUiState(
    val list: List<String> = listOf<String>(),
    val date: String = "",
    val likeCount: String = "",
    val name: String = "",
    val contents: String = "",
    val commentCount: String = "",
    val userId: Int = 0, /* 이름 클릭시 프로필 화면으로 이동하기위해 사용 */
    val reviewId: Int = 0, /* 댓글 클릭시 댓글 화면으로 불러오기위해 사용 */
)