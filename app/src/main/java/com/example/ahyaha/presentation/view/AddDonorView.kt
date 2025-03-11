package com.example.ahyaha.presentation.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ahyaha.presentation.viewmodel.AddDonorEvent
import com.example.ahyaha.presentation.viewmodel.AddDonorViewModel
import com.example.ahyaha.presentation.viewmodel.BloodTypeViewModel
import com.example.ahyaha.presentation.viewmodel.DonorViewModel

@Composable
fun AddDonorView(
    viewModel: AddDonorViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Name field
        OutlinedTextField(
            value = state.name,
            onValueChange = { viewModel.onEvent(AddDonorEvent.NameChanged(it)) },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        // Email field
        OutlinedTextField(
            value = state.email,
            onValueChange = { viewModel.onEvent(AddDonorEvent.EmailChanged(it)) },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )

        // Phone number field
        OutlinedTextField(
            value = state.phoneNumber,
            onValueChange = { viewModel.onEvent(AddDonorEvent.PhoneNumberChanged(it)) },
            label = { Text("Phone Number") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier.fillMaxWidth()
        )

        // Blood group dropdown
        OutlinedTextField(
            value = state.bloodGroup,
            onValueChange = { viewModel.onEvent(AddDonorEvent.BloodGroupChanged(it)) },
            label = { Text("Blood Group") },
            modifier = Modifier.fillMaxWidth()
        )

        // Rh factor dropdown
        OutlinedTextField(
            value = state.rh,
            onValueChange = { viewModel.onEvent(AddDonorEvent.RhChanged(it)) },
            label = { Text("Rh Factor") },
            modifier = Modifier.fillMaxWidth()
        )

        // Location field
        OutlinedTextField(
            value = state.location,
            onValueChange = { viewModel.onEvent(AddDonorEvent.LocationChanged(it)) },
            label = { Text("Location") },
            modifier = Modifier.fillMaxWidth()
        )

        // Profile picture URL field
        OutlinedTextField(
            value = state.profilePicture,
            onValueChange = { viewModel.onEvent(AddDonorEvent.ProfilePictureChanged(it)) },
            label = { Text("Profile Picture URL") },
            modifier = Modifier.fillMaxWidth()
        )

        // Submit button
        Button(
            onClick = { viewModel.onEvent(AddDonorEvent.Submit) },
            modifier = Modifier.fillMaxWidth(),
            enabled = !state.isLoading
        ) {
            Text(if (state.isLoading) "Submitting..." else "Submit")
        }

        // Error message
        if (state.error != null) {
            Text(
                text = state.error!!,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(8.dp)
            )
        }

        // Success message
        if (state.isSuccess) {
            Text(
                text = "Donor added successfully!",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun AppNavigation(
    donorViewModel: DonorViewModel,
    bloodTypeViewModel: BloodTypeViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            MainScreen(
                donorViewModel = donorViewModel,
                bloodTypeViewModel = bloodTypeViewModel,
                navController = navController
            )
        }
        composable("addDonor") {
            AddDonorView(onBack = { navController.popBackStack() })
        }
        composable("location") {
            LocationScreen(onBack = { navController.popBackStack() })
        }
        composable("donorList") {
            DonorListView(onBack = { navController.popBackStack() })
        }
    }
}