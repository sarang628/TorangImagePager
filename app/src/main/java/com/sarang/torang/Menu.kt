package com.sarang.torang

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Menu(
    a: @Composable () -> Unit = {},
    restaurantId: @Composable () -> Unit = {},
    imagePagerWithContentsTest: @Composable () -> Unit = {},
    loginRepositoryTest: @Composable () -> Unit = {},
    feedRepositoryTest1: @Composable () -> Unit = {},
){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "Menu"){
        composable("Menu"){
            Column {
                Button({
                    navController.navigate("ReviewId")
                }) {
                    Text("ReviewId")
                }

                Button({
                    navController.navigate("RestaurantId")
                }) {
                    Text("RestaurantId")
                }

                Button({
                    navController.navigate("ImagePagerWithContentsTest")
                }) {
                    Text("ImagePagerWithContentsTest")
                }

                Button({
                    navController.navigate("loginRepositoryTest")
                }) {
                    Text("loginRepositoryTest")
                }

                Button({
                    navController.navigate("feedRepositoryTest1")
                }) {
                    Text("feedRepositoryTest1")
                }

            }
        }

        composable("ReviewId") {
            a()
        }

        composable("RestaurantId") {
            restaurantId()
        }

        composable("ImagePagerWithContentsTest") {
            imagePagerWithContentsTest()
        }

        composable("loginRepositoryTest") {
            loginRepositoryTest()
        }

        composable("feedRepositoryTest1") {
            feedRepositoryTest1()
        }

    }
}