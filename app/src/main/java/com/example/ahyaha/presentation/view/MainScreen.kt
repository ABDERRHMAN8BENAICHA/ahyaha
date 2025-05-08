package com.example.ahyaha.presentation.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ahyaha.presentation.viewmodel.BloodTypeViewModel
import com.example.ahyaha.presentation.viewmodel.DonorViewModel
import com.example.ahyaha.presentation.view.components.*
import com.example.ahyaha.ui.theme.PlasmaOrange

@Composable
fun MainScreen(
    donorViewModel: DonorViewModel,
    bloodTypeViewModel: BloodTypeViewModel,
    navController: NavController,
    isLoggedIn: Boolean = false,
    modifier: Modifier = Modifier
) {
    val donorState by donorViewModel.uiState.collectAsState()
    var selectedTab by remember { mutableStateOf(0) }
    var searchText by remember { mutableStateOf("") }

    val filteredDonors = remember(searchText, donorState.donors) {
        donorState.donors.filter { it.name?.contains(searchText, ignoreCase = true) ?: false }
    }

    Scaffold(
        bottomBar = { BottomNavigationBar { selectedTab = it } },
        floatingActionButton = {
            if (isLoggedIn) {
                FloatingActionButton(
                    onClick = { navController.navigate("addDonor") },
                    containerColor = PlasmaOrange,
                    elevation = FloatingActionButtonDefaults.elevation(8.dp)
                ) {
                    Icon(Icons.Filled.Add, contentDescription = "Add a donor")
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            TopBar(
                searchText = searchText, 
                onSearchTextChanged = { searchText = it },
                onProfileClicked = { 
                    // Navigate to login when profile icon is clicked
                    navController.navigate("login")
                }
            )
            BloodTypesSection()
            ImageSection()
            RegularDonorsSection(donors = filteredDonors)
            Events()
            ActivitySection()
            RecentPostsSection()
        }
    }
}





















