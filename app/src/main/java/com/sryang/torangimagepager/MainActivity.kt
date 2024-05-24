package com.sryang.torangimagepager

import ZoomableTorangAsyncImage
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.sryang.imagepager.ImagePager
import com.sryang.library.ImagePagerWithContents
import com.sryang.torangimagepager.ui.theme.TorangImagePagerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TorangImagePagerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ImagePagerWithContentsTest()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TorangImagePagerTheme {
        Greeting("Android")
    }
}

@Composable
fun ImagePagerWithContentsTest() {
    ImagePagerWithContents(
        list = listOf(
            "http://sarang628.iptime.org:89/review_images/0/0/2023-09-14/09_19_29_616.jpg",
            "http://sarang628.iptime.org:89/review_images/0/0/2023-09-14/09_19_29_653.jpg",
            "http://sarang628.iptime.org:89/review_images/0/0/2023-09-14/09_30_07_284.jpg",
            "http://sarang628.iptime.org:89/review_images/0/0/2023-09-14/09_30_07_325.jpg",
            "http://sarang628.iptime.org:89/review_images/0/0/2023-09-14/09_59_45_505.jpg",
            "http://sarang628.iptime.org:89/review_images/0/0/2023-09-14/09_59_45_535.jpg",
            "http://sarang628.iptime.org:89/review_images/0/0/2023-09-14/10_12_56_409.jpg",
            "http://sarang628.iptime.org:89/review_images/0/0/2023-09-14/10_12_56_453.jpg",
            "http://sarang628.iptime.org:89/review_images/0/0/2023-09-14/10_14_24_779.jpg",
            "http://sarang628.iptime.org:89/review_images/0/0/2023-09-14/10_14_41_930.jpg",
            "http://sarang628.iptime.org:89/review_images/0/0/2023-09-14/10_45_42_899.jpg",
            "http://sarang628.iptime.org:89/review_images/0/0/2023-09-14/10_45_42_943.jpg",
            "http://sarang628.iptime.org:89/review_images/0/0/2023-09-14/10_45_43_007.jpg"
        ),
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
        imagePager = { list, position, onPage, backgroundColor ->
            ImagePager(
                list = list,
                position = position ?: 0,
                onPage = onPage,
                backgroundColor = backgroundColor ?: Color.Black,
                image = { url ->
                    ZoomableTorangAsyncImage(
                        model = url,
                        modifier = Modifier.fillMaxSize()
                    )
                })
        }
    )
}