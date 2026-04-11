package com.sarang.torang

import ZoomableTorangAsyncImage
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sarang.torang.data.RestaurantWithFiveImages
import com.sarang.torang.data.ReviewAndImage
import com.sarang.torang.repository.LoginRepository
import com.sarang.torang.repository.feed.FeedFlowRepository
import com.sarang.torang.repository.feed.FeedLoadRepository
import com.sarang.torang.repository.feed.FeedRepository
import com.sarang.torang.repository.test.LoginRepositoryTest
import com.sarang.torang.repository.test.feed.FeedRepositoryTest1
import com.sarang.torang.ui.component.ImagePagerWithContents
import com.sarang.torang.ui.theme.TorangImagePagerTheme
import com.sryang.imagepager.provideImagePager
import com.sryang.library.ExpandableText
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.collections.emptyList

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var loginRepository: LoginRepository
    @Inject lateinit var feedRepository: FeedRepository
    @Inject lateinit var feedLoadRepository: FeedLoadRepository
    @Inject lateinit var feedFlowRepository: FeedFlowRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            var list : List<ReviewAndImage>? by remember { mutableStateOf(null) }

            LaunchedEffect(Unit) {
                feedLoadRepository.feeds.collect {
                    list = it
                }
            }

            val contents : @Composable ()->Unit = {
                Menu(
                    a = {
                        TestContainer(list) { reviewId, restaurantId ->
                            Test(reviewId)
                        }
                    },
                    restaurantId = {
                        TestContainer(list) { reviewId, restaurantId ->
                            Test2(restaurantId)
                        }
                    },
                    imagePagerWithContentsTest = {
                        ImagePagerWithContentsTest()
                    },
                    loginRepositoryTest = {
                        LoginRepositoryTest(loginRepository = loginRepository)
                    },
                    feedRepositoryTest1 = {
                        FeedRepositoryTest1(
                            feedRepository = feedRepository,
                            feedLoadRepository = feedLoadRepository,
                            feedFlowRepository = feedFlowRepository
                        )
                    }
                )
            }


            TorangImagePagerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding))
                    {
                        contents()
                    }
                }
            }
        }
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
                modifier = Modifier.fillMaxSize(),
                onSwipeDown = {
                    Log.d("__MainActivity", "onSwipeDown")
                }
            )
        },
        onPage = {},
        expandableText = { modifier, text, expandableTextColor, onClickNickName ->
            ExpandableText(
                modifier = modifier,
                text = text,
                onClickNickName = onClickNickName,
                expandableTextColor = expandableTextColor
            )
        }
    )
}