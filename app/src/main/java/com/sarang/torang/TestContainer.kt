package com.sarang.torang

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.InputChip
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
fun TestContainer(list : List<ReviewAndImage>? = null,
                  content : @Composable (String, String)->Unit = {_,_->}, ){
    var reviewId by remember { mutableStateOf("0") }
    var restaurantId by remember { mutableStateOf("0") }
    Column {
        InputChip(selected = true, onClick = { }, label = {
            Text(text = "reviewId:")
            BasicTextField(value = reviewId, onValueChange = {
                reviewId = it
            })
        })

        InputChip(selected = true, onClick = { }, label = {
            Text(text = "restaurantId:")
            BasicTextField(value = restaurantId, onValueChange = {
                restaurantId = it
            })
        })

        list?.let {
            LazyColumn(Modifier.height(100.dp)) {
                items(it){
                    Text(
                        modifier = Modifier.clickable{
                            reviewId = it.review.reviewId.toString()
                        },
                        text = "${it.review.reviewId}")
                }
            }
        }

        content.invoke(reviewId, restaurantId)
    }
}