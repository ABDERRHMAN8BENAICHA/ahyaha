package com.example.ahyaha.presentation.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.ahyaha.presentation.viewmodel.BloodTypeViewModel
import com.example.ahyaha.presentation.viewmodel.DonorViewModel
import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import com.example.ahyaha.presentation.view.components.ActivitySection
import com.example.ahyaha.presentation.view.components.BloodTypesSection
import com.example.ahyaha.presentation.view.components.BottomNavigationBar
import com.example.ahyaha.presentation.view.components.Events
import com.example.ahyaha.presentation.view.components.ImageSection
import com.example.ahyaha.presentation.view.components.RecentPostsSection
import com.example.ahyaha.presentation.view.components.RegularDonorsSection
import com.example.ahyaha.presentation.view.components.TopBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    donorViewModel: DonorViewModel,
    bloodTypeViewModel: BloodTypeViewModel,
    modifier: Modifier = Modifier
) {
    val donorState by donorViewModel.uiState.collectAsState()
    var selectedTab by remember { mutableStateOf(0) }
    var searchText by remember { mutableStateOf("") }

    val filteredDonors = donorState.donors.filter {
        it.name.contains(searchText, ignoreCase = true)
    }

    Scaffold(
        bottomBar = { BottomNavigationBar { selectedTab = it } }
    ) { innerPadding ->

        Column(modifier = Modifier.padding(innerPadding)) {
            TopBar(searchText) { searchText = it }
            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier

                        .verticalScroll(rememberScrollState())
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

























