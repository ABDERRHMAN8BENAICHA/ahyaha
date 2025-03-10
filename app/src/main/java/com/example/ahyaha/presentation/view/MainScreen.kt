

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

@Composable
fun MainScreen(
    donorViewModel: DonorViewModel,
    bloodTypeViewModel: BloodTypeViewModel,
    navController: NavController, // ✅ تأكد من تمرير NavController
    modifier: Modifier = Modifier
) {
    val donorState by donorViewModel.uiState.collectAsState()
    var selectedTab by remember { mutableStateOf(0) }
    var searchText by remember { mutableStateOf("") }

    val filteredDonors = remember(searchText, donorState.donors) {
        donorState.donors.filter { it.name.contains(searchText, ignoreCase = true) }
    }

    Scaffold(
        bottomBar = { BottomNavigationBar { selectedTab = it } },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("addDonor") }, // ✅ التنقل للشاشة الصحيحة
                containerColor = MaterialTheme.colorScheme.primary,
                elevation = FloatingActionButtonDefaults.elevation(8.dp)
            ) {
                Icon(Icons.Filled.Add, contentDescription = "إضافة متبرع")
            }

        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            TopBar(searchText = searchText, onSearchTextChanged = { searchText = it })
            BloodTypesSection()
            ImageSection()
            RegularDonorsSection(donors = filteredDonors)
            Events()
            ActivitySection()
            RecentPostsSection()
        }
    }
}





















