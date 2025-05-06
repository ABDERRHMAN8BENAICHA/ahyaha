package com.example.ahyaha.presentation.view

import android.net.Uri
import androidx.compose.material3.MaterialTheme

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.ahyaha.presentation.viewmodel.DonorDetailEvent
import com.example.ahyaha.presentation.viewmodel.DonorDetailState
import com.example.ahyaha.presentation.viewmodel.DonorDetailViewModel
import com.example.ahyaha.ui.theme.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DonorDetailScreen(
    navController: NavController,
    viewModel: DonorDetailViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    var showDeleteConfirmationDialog by remember { mutableStateOf(false) }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            viewModel.onEvent(DonorDetailEvent.EditProfilePictureChanged(uri))
        }
    )

    LaunchedEffect(state.deleteSuccess) {
        if (state.deleteSuccess) {
            coroutineScope.launch {
                snackbarHostState.showSnackbar("Donor deleted successfully")
                viewModel.onEvent(DonorDetailEvent.ResetStatus)
                navController.popBackStack()
            }
        }
    }

    LaunchedEffect(state.saveSuccess) {
        if (state.saveSuccess) {
            coroutineScope.launch {
                snackbarHostState.showSnackbar("Changes saved successfully")
                viewModel.onEvent(DonorDetailEvent.ResetStatus)
            }
        }
    }

    LaunchedEffect(state.error, state.saveError, state.deleteError) {
        val errorMsg = state.error ?: state.saveError ?: state.deleteError
        if (errorMsg != null) {
            coroutineScope.launch {
                snackbarHostState.showSnackbar(errorMsg)
                viewModel.onEvent(DonorDetailEvent.ResetStatus)
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text(if (state.isEditing) "Edit Donor" else "Donor Details", color = BloodRed, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = BloodRed)
                    }
                },
                actions = {
                    if (state.donor != null && !state.isLoading) {
                        if (!state.isEditing) {
                            IconButton(onClick = { viewModel.onEvent(DonorDetailEvent.EnterEditMode) }) {
                                Icon(Icons.Default.Edit, contentDescription = "Edit Donor", tint = BloodRed)
                            }
                            IconButton(onClick = { showDeleteConfirmationDialog = true }) {
                                Icon(Icons.Default.Delete, contentDescription = "Delete Donor", tint = MaterialTheme.colorScheme.error)
                            }
                        } else {
                            TextButton(onClick = { viewModel.onEvent(DonorDetailEvent.ExitEditMode) }) {
                                Text("Cancel", color = BloodRed)
                            }
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        when {
            state.isLoading -> {
                Box(modifier = Modifier.fillMaxSize().padding(paddingValues), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            state.error != null || (state.donor == null && !state.isLoading) -> {
                Box(modifier = Modifier.fillMaxSize().padding(paddingValues).padding(16.dp), contentAlignment = Alignment.Center) {
                    Text(state.error ?: "Donor not found", textAlign = TextAlign.Center)
                }
            }
            state.donor != null -> {
                DonorDetailForm(
                    state = state,
                    paddingValues = paddingValues,
                    onEvent = viewModel::onEvent,
                    onImagePickRequest = { imagePickerLauncher.launch("image/*") }
                )
            }
        }
    }


    if (showDeleteConfirmationDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteConfirmationDialog = false },
            title = { Text("Confirm Deletion") },
            text = { Text("Are you sure you want to delete this donor? This action cannot be undone.") },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.onEvent(DonorDetailEvent.DeleteDonor)
                        showDeleteConfirmationDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Text("Delete")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteConfirmationDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}


@Composable
fun DonorDetailForm(
    state: DonorDetailState,
    paddingValues: PaddingValues,
    onEvent: (DonorDetailEvent) -> Unit,
    onImagePickRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    val currentDonor = state.donor!!

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(paddingValues)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                "Donor Information",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = BloodRed,
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
            )

            Text(
                text = "Profile Picture",
                style = MaterialTheme.typography.labelMedium,
                color = BloodRed.copy(alpha = 0.8f),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .border(1.dp, PlasmaOrange, CircleShape)
                        .background(MaterialTheme.colorScheme.secondaryContainer)
                        .clickable(enabled = state.isEditing) {
                            if (state.isEditing) onImagePickRequest()
                        },
                    contentAlignment = Alignment.Center
                ) {
                    val imageModel: Any = state.editedProfilePictureUri ?: currentDonor.profilePicture ?: Icons.Default.Person
                    if (imageModel is ImageVector) {
                        Icon(
                            imageVector = imageModel,
                            contentDescription = "Placeholder",
                            modifier = Modifier.size(40.dp),
                            tint = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    } else {
                        Image(
                            painter = rememberAsyncImagePainter(model = imageModel), // Coil handles Uri/String
                            contentDescription = "Profile Picture",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
                if (state.isEditing) {
                    Button(
                        onClick = onImagePickRequest,
                        colors = ButtonDefaults.buttonColors(containerColor = PlasmaOrange)
                    ) {
                        Text("Choose Picture", color=Color.White)
                    }
                }
            }

            // --- Input Fields ---
            InputField(
                label = "Full Name",
                value = if (state.isEditing) state.editedName else currentDonor.name,
                onValueChange = { if (state.isEditing) onEvent(DonorDetailEvent.EditNameChanged(it)) },
                readOnly = !state.isEditing,
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = BloodRed) },
                errorMessage = if (state.isEditing) state.saveError else null
            )
            InputField(
                label = "E-mail",
                value = if (state.isEditing) state.editedEmail else currentDonor.email,
                onValueChange = { if (state.isEditing) onEvent(DonorDetailEvent.EditEmailChanged(it)) },
                readOnly = !state.isEditing,
                leadingIcon = { Icon(Icons.Default.Email, contentDescription = null, tint = BloodRed) },
                keyboardType = KeyboardType.Email,
                errorMessage = if (state.isEditing) state.saveError else null
            )
            InputField(
                label = "Phone Number",
                value = if (state.isEditing) state.editedPhoneNumber else currentDonor.phoneNumber,
                onValueChange = {

                    if (state.isEditing && it.length <= 10 && it.all { char -> char.isDigit() }) {
                        onEvent(DonorDetailEvent.EditPhoneNumberChanged(it))
                    } else if (!state.isEditing) {

                    }
                },
                readOnly = !state.isEditing,
                leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null, tint = BloodRed) },
                keyboardType = KeyboardType.Phone,
                placeholder = "10-digit number",
                errorMessage = if (state.isEditing) state.saveError else null
            )

            Divider(color = PlasmaOrange.copy(alpha = 0.5f), thickness = 1.dp)
            Text("Blood Information", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold, color = BloodRed, modifier = Modifier.padding(top = 8.dp, bottom = 8.dp))

            // --- Dropdowns ---
            BloodTypeDropdown(
                selectedValue = if (state.isEditing) state.editedBloodGroup else currentDonor.bloodGroup,
                onValueChange = { if (state.isEditing) onEvent(DonorDetailEvent.EditBloodGroupChanged(it)) },
                enabled = state.isEditing
            )
            RhFactorDropdown(
                selectedValue = if (state.isEditing) state.editedRh else currentDonor.rh,
                onValueChange = { if (state.isEditing) onEvent(DonorDetailEvent.EditRhChanged(it)) },
                enabled = state.isEditing
            )

            Divider(color = PlasmaOrange.copy(alpha = 0.5f), thickness = 1.dp)
            Text("Contact Information", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold, color = BloodRed, modifier = Modifier.padding(top = 8.dp, bottom = 8.dp))

            InputField(
                label = "Location",
                value = if (state.isEditing) state.editedLocation else currentDonor.location,
                onValueChange = { if (state.isEditing) onEvent(DonorDetailEvent.EditLocationChanged(it)) },
                readOnly = !state.isEditing,
                leadingIcon = { Icon(Icons.Default.LocationOn, contentDescription = null, tint = BloodRed) },
                errorMessage = if (state.isEditing) state.saveError else null
            )

            // --- Save Button (only in edit mode) ---
            if (state.isEditing) {
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { onEvent(DonorDetailEvent.SaveChanges) },
                    enabled = !state.isSaving,
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = BloodRed),
                    shape = MaterialTheme.shapes.medium
                ) {
                    if (state.isSaving) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = Color.White,
                            strokeWidth = 2.dp
                        )
                    } else {
                        Text("Save Changes", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }
                // Show general save error below button
                if (state.saveError != null) {
                    Text(
                        text = state.saveError ?: "Unknown save error", // Use the string error
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp)) // Bottom spacing
        } // End Column
    } // End CompositionLocalProvider
}



@Composable
fun InputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    errorMessage: String? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    placeholder: String? = null,
    readOnly: Boolean = false
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(label, color = BloodRed.copy(alpha = 0.8f)) },
            isError = errorMessage != null && !readOnly,
            leadingIcon = leadingIcon,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            visualTransformation = visualTransformation,
            placeholder = placeholder?.let { { Text(it, color = Color.Gray.copy(alpha = 0.6f)) } },
            readOnly = readOnly,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = BloodRed,
                unfocusedBorderColor = PlasmaOrange.copy(alpha = 0.7f),
                focusedLabelColor = BloodRed,
                unfocusedLabelColor = BloodRed.copy(alpha = 0.6f),
                cursorColor = BloodRed,
                errorBorderColor = Color.Red,
                errorLabelColor = Color.Red,
                errorCursorColor = Color.Red,
                disabledTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f), // Example disabled text color
                disabledBorderColor = PlasmaOrange.copy(alpha = 0.5f),
                disabledLabelColor = BloodRed.copy(alpha = 0.5f),
                disabledLeadingIconColor = BloodRed.copy(alpha = 0.5f)
            ),
            singleLine = true,
            shape = MaterialTheme.shapes.small
        )
        if (errorMessage != null && !readOnly) {
            Text(
                errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 4.dp, top = 4.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BloodTypeDropdown(
    selectedValue: String,
    onValueChange: (String) -> Unit,
    enabled: Boolean = true
) {
    var expanded by remember { mutableStateOf(false) }
    val bloodTypes = listOf("A", "B", "AB", "O")

    Column {
        Box {
            OutlinedTextField(
                value = selectedValue,
                onValueChange = {},
                readOnly = true,
                enabled = enabled,
                label = { Text("Blood Type", color = BloodRed.copy(alpha = 0.8f)) },
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    IconButton(
                        onClick = { if (enabled) expanded = !expanded },
                        enabled = enabled
                    ) {
                        Icon(
                            Icons.Default.ArrowDropDown,
                            contentDescription = "Select Blood Type",
                            tint = if (enabled) BloodRed else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
                        )
                    }
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null,
                        tint = if (enabled) BloodRed else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    disabledTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                    disabledBorderColor = PlasmaOrange.copy(alpha = 0.5f),
                    disabledLabelColor = BloodRed.copy(alpha = 0.5f),
                    disabledLeadingIconColor = BloodRed.copy(alpha = 0.5f),
                    disabledTrailingIconColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
                    focusedBorderColor = BloodRed,
                    unfocusedBorderColor = PlasmaOrange.copy(alpha = 0.7f),
                    focusedLabelColor = BloodRed,
                    unfocusedLabelColor = BloodRed.copy(alpha = 0.6f),
                    cursorColor = BloodRed
                ),
                shape = MaterialTheme.shapes.small
            )

            Box(
                modifier = Modifier
                    .matchParentSize()
                    .clickable(enabled = enabled) {
                        if (enabled) expanded = !expanded
                    }
            )

            DropdownMenu(
                expanded = expanded && enabled,
                onDismissRequest = { expanded = false }
            ) {
                bloodTypes.forEach { bloodType ->
                    DropdownMenuItem(
                        text = { Text(bloodType, fontWeight = if (bloodType == selectedValue) FontWeight.Bold else FontWeight.Normal) },
                        onClick = {
                            onValueChange(bloodType)
                            expanded = false
                        },
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RhFactorDropdown(
    selectedValue: String,
    onValueChange: (String) -> Unit,
    enabled: Boolean = true
) {
    var expanded by remember { mutableStateOf(false) }
    val rhFactors = listOf("+", "-")

    Column {
        Box {
            OutlinedTextField(
                value = selectedValue,
                onValueChange = {},
                readOnly = true,
                enabled = enabled,
                label = { Text("Rh Factor", color = BloodRed.copy(alpha = 0.8f)) },
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    IconButton(
                        onClick = { if (enabled) expanded = !expanded }, // Respect enabled state
                        enabled = enabled
                    ) {
                        Icon(
                            Icons.Default.ArrowDropDown,
                            contentDescription = "Select Rh Factor",
                            tint = if (enabled) BloodRed else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
                        )
                    }
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = null,
                        tint = if (enabled) BloodRed else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    disabledTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                    disabledBorderColor = PlasmaOrange.copy(alpha = 0.5f),
                    disabledLabelColor = BloodRed.copy(alpha = 0.5f),
                    disabledLeadingIconColor = BloodRed.copy(alpha = 0.5f),
                    disabledTrailingIconColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
                    // Keep your focused/unfocused colors
                    focusedBorderColor = BloodRed,
                    unfocusedBorderColor = PlasmaOrange.copy(alpha = 0.7f),
                    focusedLabelColor = BloodRed,
                    unfocusedLabelColor = BloodRed.copy(alpha = 0.6f),
                    cursorColor = BloodRed
                ),
                shape = MaterialTheme.shapes.small
            )

            Box(
                modifier = Modifier
                    .matchParentSize()
                    .clickable(enabled = enabled) {
                        if (enabled) expanded = !expanded
                    }
            )

            DropdownMenu(
                expanded = expanded && enabled,
                onDismissRequest = { expanded = false }
            ) {
                rhFactors.forEach { rh ->
                    DropdownMenuItem(
                        text = { Text(rh, fontWeight = if (rh == selectedValue) FontWeight.Bold else FontWeight.Normal) },
                        onClick = {
                            onValueChange(rh)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}