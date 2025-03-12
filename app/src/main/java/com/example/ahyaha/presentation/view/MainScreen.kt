package com.example.ahyaha.presentation.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.ahyaha.presentation.viewmodel.BloodTypeViewModel
import com.example.ahyaha.presentation.viewmodel.DonorViewModel
import android.annotation.SuppressLint
import androidx.compose.foundation.verticalScroll
import com.example.ahyaha.presentation.view.components.*

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    donorViewModel: DonorViewModel,
    bloodTypeViewModel: BloodTypeViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val donorState by donorViewModel.uiState.collectAsState()
    var searchText by remember { mutableStateOf("") }

    val filteredDonors = donorState.donors.filter {
        it.name.contains(searchText, ignoreCase = true)
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar { tabIndex ->
                when (tabIndex) {
                    0 -> {}
                    1 -> navController.navigate("addDonor")
                    2 -> {}
                    3 -> {}
                }
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            TopBar(searchText) { searchText = it }
            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier.verticalScroll(rememberScrollState())
                ) {
                    BloodTypesSection()
                    ImageSection()
                    RegularDonorsSection(donors = filteredDonors)
                    Events()
                    ActivitySection()
                    RecentPostsSection()
                }
            }
        }
    }
}