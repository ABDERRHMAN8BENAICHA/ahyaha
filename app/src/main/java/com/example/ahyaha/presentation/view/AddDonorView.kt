package com.example.ahyaha.presentation.view

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.ahyaha.presentation.viewmodel.AddDonorEvent
import com.example.ahyaha.presentation.viewmodel.AddDonorViewModel

@Composable
fun AddDonorView(
    viewModel: AddDonorViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
        viewModel.onEvent(AddDonorEvent.ProfilePictureChanged(uri.toString()))
    }

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
            value = state.Rh,
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

        // Profile picture section
        if (imageUri != null) {
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(context)
                        .data(imageUri)
                        .build()
                ),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
        }

        // Button to pick an image
        Button(
            onClick = { launcher.launch("image/*") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(Icons.Default.Face , contentDescription = "Pick Image")
            Spacer(modifier = Modifier.width(8.dp))
            Text("Pick Profile Picture")
        }

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