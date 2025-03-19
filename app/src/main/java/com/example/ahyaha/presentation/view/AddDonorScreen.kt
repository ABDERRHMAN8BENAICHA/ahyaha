package com.example.ahyaha.presentation.view.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ahyaha.data.model.Donor
import com.example.ahyaha.presentation.viewmodel.DonorViewModel
import java.util.*

@Composable
fun AddDonorScreen(navController: NavController, donorViewModel: DonorViewModel) {
    var fullName by remember { mutableStateOf("") }
    var bloodType by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(value = fullName, onValueChange = { fullName = it }, label = { Text("Full Name") })
        TextField(value = bloodType, onValueChange = { bloodType = it }, label = { Text("Blood Type") })
        TextField(value = phoneNumber, onValueChange = { phoneNumber = it }, label = { Text("Phone Number") })
        TextField(value = address, onValueChange = { address = it }, label = { Text("Address") })

        Button(onClick = {
            val newDonor = Donor(
                id = UUID.randomUUID().toString(),
                name = fullName,
                phoneNumber = phoneNumber,
                bloodGroup = bloodType,
                location = address,
                email = "",
                profilePicture = null,
                Rh = "+",
                lastDonationDate = null,
                createdAt = Date(),
                updatedAt = Date()
            )

            donorViewModel.addDonor(newDonor)
            navController.popBackStack()
        }) {
            Text("Save Donor")
        }
    }
}
