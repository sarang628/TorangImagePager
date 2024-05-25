package com.sryang.library.data

data class ReviewImageEntity(
    val pictureId: Int,
    val restaurantId: Int,
    val userId: Int,
    val reviewId: Int,
    val pictureUrl: String,
    val createDate: String,
    val menuId: Int,
    val menu: Int,
)