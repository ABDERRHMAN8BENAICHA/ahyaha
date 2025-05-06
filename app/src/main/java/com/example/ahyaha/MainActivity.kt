package com.example.ahyaha

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.ahyaha.presentation.view.MainScreen
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ahyaha.presentation.view.DonorDetailScreen
import com.example.ahyaha.presentation.view.AddDonorView
import androidx.navigation.NavType
import androidx.navigation.navArgument

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "mainScreen"
            ) {
                composable("mainScreen") {
                    MainScreen(
                        donorViewModel = hiltViewModel(),
                        bloodTypeViewModel = hiltViewModel(),
                        navController = navController, // ✅ تمرير navController
                        onDonorClick = { donorId ->
                            navController.navigate("donorDetail/$donorId")
                        }
                    )
                }
                composable("addDonor") {
                    AddDonorView(navController = navController) // ✅ تمرير navController
                }

                composable(
                    route = "donorDetail/{donorId}",
                    arguments = listOf(navArgument("donorId") { type = NavType.StringType }) // Define argument type
                ) {
                    DonorDetailScreen(
                        navController = navController
                    )
                }
            }
        }
    }
}

