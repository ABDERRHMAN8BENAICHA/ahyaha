package com.example.ahyaha.presentation.view

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.ahyaha.presentation.viewmodel.AddDonorEvent
import com.example.ahyaha.presentation.viewmodel.AddDonorViewModel
import com.example.ahyaha.R  // تأكد من إضافة هذا للوصول إلى الأيقونات
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp


@Composable
fun AddDonorView(
    navController: NavController,
    viewModel: AddDonorViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current


    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Image(
                painter = painterResource(id = R.drawable.bac),
                contentDescription = "خلفية",
                modifier = Modifier
                    .size(300.dp)
                    .padding(bottom = 20.dp) //
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Fit
            )
        }


    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
            .background(Color.White.copy(alpha = 0.5f), shape = RoundedCornerShape(16.dp))
            .padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = state.name ?: "",
            onValueChange = { viewModel.onEvent(AddDonorEvent.NameChanged(it)) },
            label = { Text("name") },
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = "name") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            isError = state.error?.containsKey("name") == true
        )

        OutlinedTextField(
            value = state.email ?: "",
            onValueChange = { viewModel.onEvent(AddDonorEvent.EmailChanged(it)) },
            label = { Text("email") },
            leadingIcon = { Icon(Icons.Default.Email, contentDescription = "email") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            isError = state.error?.containsKey("email") == true
        )

        OutlinedTextField(
            value = state.phoneNumber ?: "",
            onValueChange = { viewModel.onEvent(AddDonorEvent.PhoneNumberChanged(it)) },
            label = { Text("phoneNumber") },
            leadingIcon = { Icon(Icons.Default.Phone, contentDescription = "phoneNumber") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            isError = state.error?.containsKey("phone") == true
        )

        OutlinedTextField(
            value = state.location ?: "",
            onValueChange = { viewModel.onEvent(AddDonorEvent.LocationChanged(it)) },
            label = { Text("location") },
            leadingIcon = { Icon(Icons.Default.LocationOn, contentDescription = "location") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            isError = state.error?.containsKey("location") == true
        )

        OutlinedTextField(
            value = state.profilePicture,
            onValueChange = { viewModel.onEvent(AddDonorEvent.ProfilePictureChanged(it)) },
            label = { Text("profilePicture") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
        )



        Spacer(modifier = Modifier.height(16.dp))

        //  أيقونات الزمر الدموية
        Text("\uD83E\uDE78Choose your blood type\uD83E\uDE78", style = MaterialTheme.typography.bodyLarge)
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            listOf(
                "A+" to R.drawable.blood_type_a, "A-" to R.drawable.blood_a1,
                "B+" to R.drawable.blood_b1, "B-" to R.drawable.blood_type_b,
                "AB+" to R.drawable.blood_type_ab,
            ).forEach { (bloodType, icon) ->
                IconButton(
                    onClick = {
                        val (group, rh) = bloodType.partition { it.isLetter() }
                        viewModel.onEvent(AddDonorEvent.BloodGroupChanged(group))
                        viewModel.onEvent(AddDonorEvent.RhChanged(rh))
                    }
                ) {
                    Image(
                        painter = painterResource(id = icon),
                        contentDescription = bloodType,
                        modifier = Modifier.size(150.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // زر الإضافة
        Button(
            onClick = { viewModel.onEvent(AddDonorEvent.Submit) },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFE55656),
                contentColor = Color.White
            )
        ) {
            Text("Add the donor")
        }


        state.error?.get("general")?.let {
            Text(it, color = Color.Red, modifier = Modifier.padding(top = 8.dp))
        }

        if (state.isSuccess) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(2.dp, Color(0xFF81C784)),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF2E7D32))
            ) {
                Text(
                    text = "Donor added successfully!",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                )
            }
        }


    }
}









