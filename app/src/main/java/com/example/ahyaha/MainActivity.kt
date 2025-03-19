package com.example.ahyaha

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ahyaha.presentation.view.components.AddDonorScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ahyaha.presentation.view.MainScreen
import com.example.ahyaha.presentation.viewmodel.BloodTypeViewModel
import com.example.ahyaha.presentation.viewmodel.DonorViewModel
import com.example.ahyaha.ui.theme.AhyahaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AhyahaTheme {
                val navController: NavHostController = rememberNavController()
                val donorViewModel = hiltViewModel<DonorViewModel>()
                hiltViewModel<BloodTypeViewModel>()

                Scaffold(modifier = Modifier.fillMaxSize()) {
                    NavHost(navController = navController, startDestination = "home") {
                        composable(route = "home") {
                            MainScreen(donorViewModel, navController)
                        }
                        composable(route = "donors") {
                            AddDonorScreen(navController, donorViewModel)
                        }

                    }

                }

            }
        }
    }
}
