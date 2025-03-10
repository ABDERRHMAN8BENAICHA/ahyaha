package com.example.ahyaha

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.ahyaha.ui.theme.AhyahaTheme
import com.example.ahyaha.presentation.view.MainScreen
import com.example.ahyaha.presentation.viewmodel.BloodTypeViewModel
import com.example.ahyaha.presentation.viewmodel.DonorViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ahyaha.presentation.view.AddDonorView

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
                        navController = navController // ✅ تمرير navController
                    )
                }
                composable("addDonor") {
                    AddDonorView(navController = navController) // ✅ تمرير navController
                }
            }
        }
    }
}

