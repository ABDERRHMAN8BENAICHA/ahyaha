package com.example.ahyaha.presentation.view

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import kotlinx.coroutines.launch
import com.example.ahyaha.presentation.viewmodel.AddDonorEvent
import com.example.ahyaha.presentation.viewmodel.AddDonorViewModel
import com.example.ahyaha.ui.theme.*
import com.google.firebase.firestore.FirebaseFirestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDonorView(
    navController: NavController,
    viewModel: AddDonorViewModel = hiltViewModel()
) {
    val db = FirebaseFirestore.getInstance()
    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    val nameFieldFocus = remember { FocusRequester() }

    // Request focus on name field when screen loads
    LaunchedEffect(Unit) {
        nameFieldFocus.requestFocus()
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState) { data ->
                Snackbar(
                    snackbarData = data,
                    containerColor = if (state.isSuccess) LifeGreen else BloodRed,
                    contentColor = Color.White,
                    shape = MaterialTheme.shapes.medium
                )
            }
        },
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            "Add a Donor",
                            color = BloodRed,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = BloodRed)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .padding(paddingValues)
                    .verticalScroll(scrollState),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Form title with nice spacing
                Text(
                    "Donor Information",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = BloodRed,
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                )

                // Personal Information Section
                InputField(
                    label = "Full Name",
                    value = state.name,
                    onValueChange = { viewModel.onEvent(AddDonorEvent.NameChanged(it)) },
                    errorMessage = state.error?.get("name"),
                    leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = BloodRed) },
                    modifier = Modifier.focusRequester(nameFieldFocus)
                )

                InputField(
                    label = "E-mail",
                    value = state.email,
                    onValueChange = { viewModel.onEvent(AddDonorEvent.EmailChanged(it)) },
                    errorMessage = state.error?.get("email"),
                    leadingIcon = { Icon(Icons.Default.Email, contentDescription = null, tint = BloodRed) },
                    keyboardType = KeyboardType.Email
                )

                InputField(
                    label = "Phone Number",
                    value = state.phoneNumber.take(10),
                    onValueChange = {
                        if (it.length <= 10 && it.all { char -> char.isDigit() }) {
                            viewModel.onEvent(AddDonorEvent.PhoneNumberChanged(it))
                        }
                    },
                    errorMessage = state.error?.get("phoneNumber"),
                    leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null, tint = BloodRed) },
                    keyboardType = KeyboardType.Phone,
                    placeholder = "10-digit number"
                )

                // Blood Information Section with divider
                Divider(color = PlasmaOrange.copy(alpha = 0.5f), thickness = 1.dp)

                Text(
                    "Blood Information",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = BloodRed,
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                )

                // Blood Type Dropdown - Now in column instead of row
                BloodTypeDropdown(
                    selectedValue = state.bloodGroup,
                    onValueChange = { viewModel.onEvent(AddDonorEvent.BloodGroupChanged(it)) }
                )

                // Rh Factor Dropdown - Now in column instead of row
                RhFactorDropdown(
                    selectedValue = state.rh,
                    onValueChange = { viewModel.onEvent(AddDonorEvent.RhChanged(it)) }
                )

                // Location Section with divider
                Divider(color = PlasmaOrange.copy(alpha = 0.5f), thickness = 1.dp)

                Text(
                    "Contact Information",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = BloodRed,
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                )

                InputField(
                    label = "Location",
                    value = state.location,
                    onValueChange = { viewModel.onEvent(AddDonorEvent.LocationChanged(it)) },
                    errorMessage = state.error?.get("location"),
                    leadingIcon = { Icon(Icons.Default.LocationOn, contentDescription = null, tint = BloodRed) }
                )

                InputField(
                    label = "Profile Picture URL",
                    value = state.profilePicture,
                    onValueChange = { viewModel.onEvent(AddDonorEvent.ProfilePictureChanged(it)) },
                    leadingIcon = { Icon(Icons.Default.AccountCircle, contentDescription = null, tint = BloodRed) },
                    placeholder = "Optional"
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Submit Button with better styling
                Button(
                    onClick = {
                        val requiredFields = listOf(
                            state.name to "Name",
                            state.email to "Email",
                            state.phoneNumber to "Phone Number",
                            state.bloodGroup to "Blood Type",
                            state.rh to "Rh Factor",
                            state.location to "Location"
                        )

                        val missingFields = requiredFields
                            .filter { (value, _) -> value.isBlank() }
                            .map { (_, name) -> name }

                        if (missingFields.isNotEmpty()) {
                            val message = "Please fill in: ${missingFields.joinToString(", ")}"
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar(message)
                            }
                        } else {
                            // Create donor data map
                            val donor = hashMapOf(
                                "name" to state.name,
                                "email" to state.email,
                                "phoneNumber" to state.phoneNumber,
                                "bloodGroup" to state.bloodGroup,
                                "rh" to state.rh,
                                "location" to state.location,
                                "profilePicture" to state.profilePicture,
                                "createdAt" to com.google.firebase.Timestamp.now()
                            )

                            // Add donor to Firebase
                            db.collection("donors")
                                .add(donor)
                                .addOnSuccessListener {
                                    viewModel.onEvent(AddDonorEvent.Submit)
                                }
                                .addOnFailureListener { e ->
                                    coroutineScope.launch {
                                        snackbarHostState.showSnackbar("Error adding donor: ${e.message}")
                                    }
                                }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = BloodRed),
                    enabled = !state.isLoading,
                    shape = MaterialTheme.shapes.medium
                ) {
                    if (state.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = Color.White,
                            strokeWidth = 2.dp
                        )
                    } else {
                        Text(
                            "Register as Donor",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                // Spacing at the bottom for better UX
                Spacer(modifier = Modifier.height(16.dp))

                // Show success notification and navigate back if donor is added
                LaunchedEffect(state.isSuccess) {
                    if (state.isSuccess) {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Donor added successfully!")
                            // Add a small delay before navigation
                            kotlinx.coroutines.delay(100)
                            navController.popBackStack()
                        }
                    }
                }

                // Show error messages if any
                LaunchedEffect(state.error) {
                    if (state.error != null && state.error!!.isNotEmpty()) {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Please correct the errors in the form")
                        }
                    }
                }
            }
        }
    }
}
//------------------------------------------------------------------------------------------------------------------------------------
// Enhanced Input Field with more parameters
@Composable
fun InputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    errorMessage: String? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    placeholder: String? = null,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label, color = BloodRed.copy(alpha = 0.8f)) },
            modifier = Modifier.fillMaxWidth(),
            isError = errorMessage != null,
            leadingIcon = leadingIcon,
            visualTransformation = visualTransformation,
            placeholder = placeholder?.let { { Text(it, color = Color.Gray.copy(alpha = 0.6f)) } },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = BloodRed,
                unfocusedBorderColor = PlasmaOrange.copy(alpha = 0.7f),
                focusedLabelColor = BloodRed,
                unfocusedLabelColor = BloodRed.copy(alpha = 0.6f),
                cursorColor = BloodRed,
                errorBorderColor = Color.Red,
                errorLabelColor = Color.Red,
                errorCursorColor = Color.Red
            ),
            singleLine = true,
            shape = MaterialTheme.shapes.small
        )
        if (errorMessage != null) {
            Text(
                errorMessage,
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 4.dp, top = 4.dp)
            )
        }
    }
}

// Enhanced Blood Type Dropdown with exposed expanded state
@Composable
fun BloodTypeDropdown(selectedValue: String, onValueChange: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val bloodTypes = listOf("A", "B", "AB", "O")

    Column {
        Box {
            OutlinedTextField(
                value = selectedValue,
                onValueChange = {},
                readOnly = true,
                label = { Text("Blood Type", color = BloodRed.copy(alpha = 0.8f)) },
                modifier = Modifier
                    .fillMaxWidth(),
                trailingIcon = {
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(
                            Icons.Default.ArrowDropDown,
                            contentDescription = "Select",
                            tint = BloodRed
                        )
                    }
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null,
                        tint = BloodRed
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = BloodRed,
                    unfocusedBorderColor = PlasmaOrange.copy(alpha = 0.7f),
                    focusedLabelColor = BloodRed,
                    unfocusedLabelColor = BloodRed.copy(alpha = 0.6f),
                    cursorColor = BloodRed
                ),
                shape = MaterialTheme.shapes.small
            )

            // Add an invisible clickable box to ensure the entire field is clickable
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .clickable { expanded = !expanded }
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.width(IntrinsicSize.Min)
            ) {
                bloodTypes.forEach { bloodType ->
                    DropdownMenuItem(
                        text = { Text(bloodType, fontWeight = if (bloodType == selectedValue) FontWeight.Bold else FontWeight.Normal) },
                        onClick = {
                            onValueChange(bloodType)
                            expanded = false
                        },
                        colors = MenuDefaults.itemColors(
                            textColor = if (bloodType == selectedValue) BloodRed else Color.Black
                        )
                    )
                }
            }
        }
    }
}

// Enhanced Rh Factor Dropdown with improved dropdown visibility
@Composable
fun RhFactorDropdown(selectedValue: String, onValueChange: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val rhFactors = listOf("+", "-")

    Column {
        Box {
            OutlinedTextField(
                value = selectedValue,
                onValueChange = {},
                readOnly = true,
                label = { Text("Rh Factor", color = BloodRed.copy(alpha = 0.8f)) },
                modifier = Modifier
                    .fillMaxWidth(),
                trailingIcon = {
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(
                            Icons.Default.ArrowDropDown,
                            contentDescription = "Select",
                            tint = BloodRed
                        )
                    }
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = null,
                        tint = BloodRed
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = BloodRed,
                    unfocusedBorderColor = PlasmaOrange.copy(alpha = 0.7f),
                    focusedLabelColor = BloodRed,
                    unfocusedLabelColor = BloodRed.copy(alpha = 0.6f),
                    cursorColor = BloodRed
                ),
                shape = MaterialTheme.shapes.small
            )

            // Add an invisible clickable box to ensure the entire field is clickable
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .clickable { expanded = !expanded }
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                rhFactors.forEach { rh ->
                    DropdownMenuItem(
                        text = { Text(rh, fontWeight = if (rh == selectedValue) FontWeight.Bold else FontWeight.Normal) },
                        onClick = {
                            onValueChange(rh)
                            expanded = false
                        },
                        colors = MenuDefaults.itemColors(
                            textColor = if (rh == selectedValue) BloodRed else Color.Black
                        )
                    )
                }
            }
        }
    }
}