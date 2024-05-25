package com.sryang.torangimagepager

import ZoomableTorangAsyncImage
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.InputChip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sarang.torang.repository.FeedRepository
import com.sarang.torang.repository.FeedRepositoryTest
import com.sarang.torang.repository.LoginRepository
import com.sarang.torang.repository.LoginRepositoryTest
import com.sryang.imagepager.provideImagePager
import com.sryang.library.ui.component.ImagePagerWithContents
import com.sryang.library.provideRestaurantImagePager
import com.sryang.library.provideReviewImagePager
import com.sryang.torangimagepager.ui.theme.TorangImagePagerTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var loginRepository: LoginRepository

    @Inject
    lateinit var feedRepository: FeedRepository

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var reviewId by remember { mutableStateOf("0") }
            var imageId by remember { mutableStateOf("0") }
            TorangImagePagerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .verticalScroll(rememberScrollState())
                    )
                    {

                        //ImagePagerWithContentsTest()
                        InputChip(selected = true, onClick = { }, label = {
                            Text(text = "reviewId:")
                            BasicTextField(value = reviewId, onValueChange = {
                                try {
                                    reviewId = it
                                } catch (e: Exception) {
                                    Log.e("__MainActivity", "Wrong input : ${it}")
                                }
                            })
                        })

                        InputChip(selected = true, onClick = { }, label = {
                            Text(text = "imageId:")
                            BasicTextField(value = imageId, onValueChange = {
                                try {
                                    imageId = it
                                } catch (e: Exception) {
                                    Log.e("__MainActivity", "Wrong input : ${it}")
                                }
                            })
                        })

                        Box(modifier = Modifier.size(600.dp)) {
                            provideReviewImagePager(
                                image = { url ->
                                    ZoomableTorangAsyncImage(
                                        model = url,
                                        modifier = Modifier.fillMaxSize()
                                    )
                                },
                                imagePager = provideImagePager()
                            ).invoke(reviewId.toInt(), 0)
                        }
                        Box(modifier = Modifier.size(600.dp)) {
                            provideRestaurantImagePager(
                                image = { url ->
                                    ZoomableTorangAsyncImage(
                                        model = url,
                                        modifier = Modifier.fillMaxSize()
                                    )
                                },
                                imagePager = provideImagePager()
                            ).invoke(reviewId.toInt())
                        }
                        LoginRepositoryTest(loginRepository = loginRepository)
                        FeedRepositoryTest(feedRepository = feedRepository)
                    }
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
        imagePager = provideImagePager(),
        image = { url ->
            ZoomableTorangAsyncImage(
                model = url,
                modifier = Modifier.fillMaxSize()
            )
        }
    )
}