package com.sryang.library

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.sryang.library.ui.component.ImagePagerWithContents
import com.sryang.library.uistate.RestaurantImagePagerUiState
import com.sryang.library.viewmodel.RestaurantImagePagerViewModel

fun provideRestaurantImagePager(
    image: @Composable (String) -> Unit,
    imagePager: @Composable (
        list: List<String>,
        position: Int?,
        onPage: ((Int) -> Unit)?,
        backgroundColor: Color?,
        image: @Composable (String) -> Unit,
    ) -> Unit,
    onName: (Int) -> Unit,
    onDate: () -> Unit,
    onContents: () -> Unit,
    onLike: (Int) -> Unit,
    onComment: (Int) -> Unit,
    expandableText: @Composable (modifier: Modifier, text: String, expandableTextColor: Color, onClickNickName: () -> Unit) -> Unit,
): @Composable (Int) -> Unit = { imageId ->
    val viewModel: RestaurantImagePagerViewModel = hiltViewModel()
    val uiState: RestaurantImagePagerUiState by viewModel.uiState.collectAsState()
    LaunchedEffect(key1 = imageId) {
        viewModel.load(imageId)
    }

    if (uiState.list.isEmpty()) {

    } else {
        ImagePagerWithContents(
            list = uiState.list.map { it.pictureUrl },
            date = uiState.contents,
            likeCount = uiState.likeCount,
            name = uiState.name,
            contents = uiState.contents,
            commentCount = uiState.commentCount,
            imagePager = imagePager,
            image = image,
            position = uiState.position,
            onPage = { viewModel.onPage(it) },
            onComment = { onComment(uiState.reviewId) },
            onName = { onName(uiState.userId) },
            onLike = { onLike(uiState.reviewId) },
            onDate = onDate,
            onContents = onContents,
            expandableText = expandableText
        )
    }
}