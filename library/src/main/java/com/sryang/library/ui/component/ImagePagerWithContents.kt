package com.sryang.library.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.sryang.library.R

@Composable
fun ImagePagerWithContents(
    list: List<String>,
    position: Int = 0,
    date: String, // MAY 10 AT 6:40 PM,
    likeCount: String, //"1.7K"
    name: String, // Torang
    contents: String, // contents,
    commentCount: String,//"762 comments"
    onName: () -> Unit,
    onDate: () -> Unit,
    onContents: () -> Unit,
    onLike: () -> Unit,
    onComment: () -> Unit,
    onPage: (Int) -> Unit,
    imagePager: @Composable (
        list: List<String>,
        position: Int?,
        onPage: ((Int) -> Unit)?,
        backgroundColor: Color?,
        image: @Composable (String) -> Unit,
    ) -> Unit,
    image: @Composable (String) -> Unit,
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        constraintSet = imagePagerConstraintSet()
    ) {
        Scaffold(
            Modifier
                .fillMaxSize(),
            containerColor = Color.Transparent

        ) {
            Box(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .background(Color.Transparent),
            ) {


                imagePager.invoke(list, position, onPage, null, image)

                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(start = 12.dp, end = 12.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .layoutId("name")
                            .clickable {
                                onName.invoke()
                            },
                        text = name, color = Color.White, fontWeight = FontWeight.Bold
                    )

                    Text(
                        modifier = Modifier
                            .layoutId("contents")
                            .clickable {
                                onContents.invoke()
                            },
                        text = contents, color = Color.White, fontSize = 12.sp
                    )

                    Text(
                        modifier = Modifier
                            .layoutId("date")
                            .clickable {
                                onDate.invoke()
                            },
                        text = date, color = Color.LightGray, fontSize = 12.sp
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                modifier = Modifier
                                    .size(15.dp)
                                    .clickable {
                                        onLike.invoke()
                                    },
                                painter = painterResource(id = R.drawable.heart_filled),
                                contentDescription = "",
                                tint = Color.Red
                            )
                            Spacer(modifier = Modifier.width(3.dp))
                            Text(text = likeCount, color = Color.White, fontSize = 12.sp)
                        }
                        Text(
                            text = commentCount,
                            color = Color.White,
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .clickable {
                                    onComment.invoke()
                                }, fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }
}

fun imagePagerConstraintSet(): ConstraintSet {
    return ConstraintSet {
        val imagePager = createRefFor("imagePager")
        val name = createRefFor("name")
        val date = createRefFor("date")
        val contents = createRefFor("contents")

        constrain(imagePager) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
        }

        constrain(contents) {
            top.linkTo(name.bottom)
        }

        constrain(date) {
            top.linkTo(contents.bottom)
        }
    }
}

@Preview
@Composable
fun PreviewImagePagerWithContents() {
    ImagePagerWithContents(
        list = listOf(),
        position = 0,
        date = "MAY 10 AT 6:40 PM",
        likeCount = "1.7K",
        name = "Torang",
        contents = "contents",
        commentCount = "762 comments",
        onName = {},
        onDate = {},
        onContents = {},
        onLike = {},
        onComment = {},
        imagePager = { _, _, _, _, _ ->
        },
        image = {},
        onPage = {}
    )
}