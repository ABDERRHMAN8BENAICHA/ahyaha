package com.example.ahyaha

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ahyaha.ui.theme.AhyahaTheme
import com.example.ahyaha.view.MainScreen
import com.example.ahyaha.viewmodel.BloodTypeViewModel
import com.example.ahyaha.viewmodel.DonorViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val donorViewModel: DonorViewModel = viewModel()
            val bloodTypeViewModel: BloodTypeViewModel = viewModel()
            AhyahaTheme {
                var searchQuery by remember { mutableStateOf("") }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = { BottomNavigationBar() } // إضافة شريط التنقل السفلي
                ) { innerPadding ->
                    val donorViewModel = viewModel<DonorViewModel>()
                    val bloodTypeViewModel = viewModel<BloodTypeViewModel>()

                    Column(modifier = Modifier.padding(innerPadding)) {
                        // تمرير المتغيرات إلى MainScreen
                        MainScreen(
                            donorViewModel = donorViewModel,
                            bloodTypeViewModel = bloodTypeViewModel,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar() {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = true,
            onClick = { /* التعامل مع النقر */ }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.LocationOn, contentDescription = "Map") },
            label = { Text("Map") },
            selected = false,
            onClick = { /* التعامل مع النقر */ }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
            label = { Text("Profile") },
            selected = false,
            onClick = { /* التعامل مع النقر */ }
        )
    }
}