package com.example.ahyaha.presentation.view

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ahyaha.presentation.viewmodel.BloodTypeViewModel
import com.example.ahyaha.presentation.viewmodel.DonorViewModel

@Composable
fun NavigationGraph(
    donorViewModel: DonorViewModel,
    bloodTypeViewModel: BloodTypeViewModel
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            MainScreen(
                donorViewModel = donorViewModel,
                bloodTypeViewModel = bloodTypeViewModel,
                navController = navController
            )
        }
        composable("addDonor") {
            AddDonorView(
                onBack = { navController.popBackStack() }
            )
        }
       // more routes for the future
    }
}