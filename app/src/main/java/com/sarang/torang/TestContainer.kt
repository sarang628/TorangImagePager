package com.sarang.torang

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.InputChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sarang.torang.data.ReviewAndImage

@Composable
fun TestContainer(
    list: List<ReviewAndImage>? = null,
    content: @Composable (String, String) -> Unit = { _, _ -> },
) {
    var reviewId by remember { mutableStateOf("0") }
    var imageId by remember { mutableStateOf("0") }
    Box {
        content.invoke(reviewId, imageId)
        Column {
            InputChip(selected = true, onClick = { }, label = {
                Text(text = "reviewId:")
                BasicTextField(value = reviewId, onValueChange = {
                    reviewId = it
                })
            })

            InputChip(selected = true, onClick = { }, label = {
                Text(text = "imageId:")
                BasicTextField(value = imageId, onValueChange = {
                    imageId = it
                })
            })

            list?.let {
                Row {
                    LazyColumn(Modifier.height(100.dp).background(MaterialTheme.colorScheme.background)) {
                        item { Text("reviewId") }
                        items(it) {
                            Text(modifier = Modifier.clickable { reviewId = it.review.reviewId.toString() },
                                 text     = "${it.review.reviewId}")
                        }
                    }
                    Spacer(Modifier.width(8.dp))
                    LazyColumn(Modifier.height(100.dp).background(MaterialTheme.colorScheme.background)) {
                        item { Text("imageId") }
                        items(it.flatMap { it.images }) {
                            Text(modifier = Modifier.clickable { imageId = it.pictureId.toString() },
                                 text     = "${it.pictureId}")
                        }
                    }
                }
            }
        }
    }
}