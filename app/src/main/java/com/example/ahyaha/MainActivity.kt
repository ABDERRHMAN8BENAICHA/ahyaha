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
import com.example.ahyaha.ui.theme.AhyahaTheme
import com.example.ahyaha.presentation.view.MainScreen
import com.example.ahyaha.presentation.viewmodel.BloodTypeViewModel
import com.example.ahyaha.presentation.viewmodel.DonorViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AhyahaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val donorViewModel = hiltViewModel<DonorViewModel>()
                    val bloodTypeViewModel = hiltViewModel<BloodTypeViewModel>()


                    MainScreen(
                        donorViewModel,
                        bloodTypeViewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
} 