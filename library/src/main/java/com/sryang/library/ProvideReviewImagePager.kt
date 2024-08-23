package com.sryang.library

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.sryang.library.ui.component.ImagePagerWithContents
import com.sryang.library.viewmodel.ImagePagerViewModel

fun provideReviewImagePager(
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
    onPage: (Int) -> Unit,
    expandableText: @Composable (modifier: Modifier, text: String, expandableTextColor: Color, onClickNickName: () -> Unit) -> Unit,
): @Composable (Int, Int) -> Unit = { reviewId, position ->
    val viewModel: ImagePagerViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()
    LaunchedEffect(key1 = reviewId) {
        viewModel.load(reviewId)
    }

    Log.d("__ImagePager", "ProvideImagePager: $uiState, position : $position")

    if (uiState.list.isEmpty()) {

    } else {
        ImagePagerWithContents(
            list = uiState.list,
            date = uiState.date,
            likeCount = uiState.likeCount,
            name = uiState.name,
            contents = uiState.contents,
            commentCount = uiState.commentCount,
            position = position,
            imagePager = imagePager,
            image = image,
            onComment = { onComment(uiState.reviewId) },
            onName = { onName(uiState.userId) },
            onLike = { onLike(uiState.reviewId) },
            onDate = onDate,
            onContents = onContents,
            onPage = onPage,
            expandableText = expandableText
        )
    }
}