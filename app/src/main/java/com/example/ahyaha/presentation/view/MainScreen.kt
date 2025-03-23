package com.example.ahyaha.presentation.view

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.ahyaha.presentation.view.components.*
import com.example.ahyaha.presentation.viewmodel.DonorViewModel
import androidx.navigation.NavController


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(donorViewModel: DonorViewModel, navController: NavController) {
    val donorState by donorViewModel.donors.collectAsState(initial = emptyList())
    var searchText by remember { mutableStateOf("") }
    val filteredDonors = donorState.filter {
        it.name.contains(searchText, ignoreCase = true)
    }

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
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
                    RecentEvents()
                    ActivitySection()
                    RecentPostsSection()
                }
            }
        }
    }
}
