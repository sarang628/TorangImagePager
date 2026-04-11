package com.sarang.torang

import ZoomableTorangAsyncImage
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sryang.imagepager.provideImagePager
import com.sryang.library.ExpandableText

@Composable
fun PicturesByReviewId(reviewId : String){
    Box(modifier = Modifier.fillMaxSize()) {
        provideReviewImagePager(
            image = { url ->
                ZoomableTorangAsyncImage(
                    model = url,
                    modifier = Modifier.fillMaxSize()
                )
            },
            imagePager = provideImagePager(),
            onName = {
                Log.d("__MainActivity", "onName userId: ${it}")
            },
            onLike = {},
            onDate = {},
            onContents = {},
            onPage = {},
            onComment = {},
            expandableText = { modifier, text, expandableTextColor, onClickNickName ->
                ExpandableText(
                    modifier = modifier,
                    text = text,
                    onClickNickName = onClickNickName,
                    expandableTextColor = expandableTextColor
                )
            }
        ).invoke(
            try {
                reviewId.toInt()
            } catch (e: Exception) {
                0
            }, 0
        )
    }
}