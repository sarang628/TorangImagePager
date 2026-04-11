package com.sarang.torang

import ZoomableTorangAsyncImage
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sryang.imagepager.provideImagePager
import com.sryang.library.ExpandableText

@Composable
fun Test2(restaurantId : String){
    Box(modifier = Modifier.size(600.dp)) {
        provideRestaurantImagePager(
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
                restaurantId.toInt()
            } catch (e: Exception) {
                0
            }
        )
    }
}