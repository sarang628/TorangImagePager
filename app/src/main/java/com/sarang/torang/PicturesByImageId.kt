package com.sarang.torang

import ZoomableTorangAsyncImage
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sryang.imagepager.provideImagePager
import com.sryang.library.ExpandableText

@Composable
fun PicturesByImageId(imageId : String){
    Box(modifier = Modifier.fillMaxSize()) {
        provideRestaurantImagePager(
            image = { url -> ZoomableTorangAsyncImage(model = url,
                                                      modifier = Modifier.fillMaxSize()) },
            imagePager = provideImagePager(),
            onName = {},
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
        ).invoke(try { imageId.toInt() } catch (e: Exception) { 0 })
    }
}