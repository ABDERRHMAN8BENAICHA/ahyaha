package com.example.ahyaha.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.ahyaha.R
import com.example.ahyaha.model.BloodType
import com.example.ahyaha.model.Donor
import com.example.ahyaha.viewmodel.BloodTypeViewModel
import com.example.ahyaha.viewmodel.DonorViewModel

@Composable
fun MainScreen(
    donorViewModel: DonorViewModel,
    bloodTypeViewModel: BloodTypeViewModel,
    modifier: Modifier = Modifier
) {
    val donorState by donorViewModel.uiState.collectAsState()
    val bloodTypeState by bloodTypeViewModel.bloodTypeState.collectAsState()

    //search related values or whatever they called idk ...

    var searchValue by remember { mutableStateOf("") }
    var barIsVisible by remember { mutableStateOf(false) }

    val filteredDonors = donorState.donors.filter { donor ->
        donor.name.contains(searchValue, ignoreCase = true)
    }



    LazyColumn(modifier = modifier.fillMaxSize()) {
        // Header with Icons
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    modifier = Modifier.clickable { /* Handle search click */
                        barIsVisible = !barIsVisible
                    }
                )
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Notifications",
                    modifier = Modifier.clickable { /* Handle notifications click */ }
                )
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Settings",
                    modifier = Modifier.clickable { /* Handle settings click */ }
                )
            }
        }

        //weee


        if (barIsVisible){
              item {
                  SearchBar(
                      queryValue = searchValue,
                      onQueryValueChange = {
                              newValue ->
                          searchValue = newValue
                      },
                      modifier = Modifier.fillMaxWidth()
                  )
              }

            item {
                if (filteredDonors.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No donors found",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                    }
                } else {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        filteredDonors.forEach { donor ->
                            DonorCard(donor = donor)
                        }
                    }
                }
            }
        }else{
            // Loading and BloodType list
            item {
                if (bloodTypeState.bloodTypes.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No blood types available",
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                    }
                } else {
                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(10.dp)
                        ) {
                            Text(
                                text = "Blood Group",
                                style = MaterialTheme.typography.headlineSmall,
                                modifier = Modifier.padding(10.dp)
                            )
                            Icon(
                                contentDescription = "arrow",
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward
                            )
                        }
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        ) {
                            items(bloodTypeState.bloodTypes) { bloodType ->
                                BloodTypeSection(bloodType = bloodType)
                            }
                        }
                    }
                }
            }

            // News Section
            item {
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(10.dp)
                    ) {
                        Text(
                            text = "News ",
                            style = MaterialTheme.typography.headlineSmall,
                            modifier = Modifier.padding(10.dp)
                        )
                        Icon(
                            contentDescription = "arrow",
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward
                        )
                    }
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        items(3) {
                            NewsSection(
                                modifier = Modifier
                                    .fillParentMaxWidth(0.8f)
                                    .padding(8.dp)
                            )
                        }
                    }
                }
            }

            // Loading and Donor List 1
            item {
                if (donorState.isLoading) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(10.dp)
                        ) {
                            Text(
                                text = "Our Donors",
                                style = MaterialTheme.typography.headlineSmall,
                                modifier = Modifier.padding(10.dp)
                            )
                            Icon(
                                contentDescription = "arrow",
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward
                            )
                        }
                        LazyRow(modifier = Modifier.fillMaxWidth()) {
                            items(donorState.donors) { donor ->
                                DonorCard(donor = donor)
                            }
                        }
                    }
                }
            }

            // Loading and Donor List 2
            item {
                if (bloodTypeState.bloodTypes.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No blood types available",
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                    }
                } else {
                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(10.dp)
                        ) {
                            Text(
                                text = "Blood Group",
                                style = MaterialTheme.typography.headlineSmall,
                                modifier = Modifier.padding(10.dp)
                            )
                            Icon(
                                contentDescription = "arrow",
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward
                            )
                        }
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        ) {
                            items(bloodTypeState.bloodTypes) { bloodType ->
                                BloodTypeSection(bloodType = bloodType)
                            }
                        }
                    }
                }
            }

            // Loading and Donor List 3
            item {
                if (bloodTypeState.bloodTypes.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No blood types available",
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                    }
                } else {
                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(10.dp)
                        ) {
                            Text(
                                text = "Blood Group",
                                style = MaterialTheme.typography.headlineSmall,
                                modifier = Modifier.padding(10.dp)
                            )
                            Icon(
                                contentDescription = "arrow",
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward
                            )
                        }
                        Box (
                            modifier = Modifier.fillMaxWidth().height(200.dp)
                        ){
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(8.dp)
                            ) {
                                items(bloodTypeState.bloodTypes) { bloodType ->
                                    BloodTypeSection(bloodType = bloodType)
                                }
                            }
                        }
                    }
                }
            }
        }
        }

}

@Composable
fun BloodTypeSection(bloodType : BloodType ){
    val bloodTypeIcon = when ("${bloodType.bloodGroup}${bloodType.Rh}") {
        "A+" -> R.drawable.a_plus
        "B+" -> R.drawable.b_plus
        "AB+" -> R.drawable.ab
        "O+" -> R.drawable.o_p
        "A-" -> R.drawable.a_m
        "B-" -> R.drawable.b_m
        "AB-" -> R.drawable.ab
        "O-" -> R.drawable.o_m
        else -> R.drawable.ab // Fallback icon
    }

   Box {
       Card (
           modifier = Modifier.width(100.dp).padding(8.dp),
           elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
       ) {
           Column (
               modifier = Modifier.padding(8.dp),
               horizontalAlignment = Alignment.CenterHorizontally
           ) {
               Image(
                   painter = painterResource(id = bloodTypeIcon),
                   modifier = Modifier.size(32.dp),
                   contentScale = ContentScale.Crop,
                   contentDescription = ""
               )
               Spacer(modifier = Modifier.height(8.dp))

               // Blood Type Text (e.g., "A+", "B-")
               Text(
                   text = "${bloodType.bloodGroup}${bloodType.Rh}",
                   style = MaterialTheme.typography.titleMedium,
                   color = MaterialTheme.colorScheme.onSurface,
                   textAlign = TextAlign.Center
               )
           }

       }
   }
}

@Composable
fun NewsSection(modifier: Modifier){
     Card (
         modifier = modifier
             .fillMaxWidth()
             .height(200.dp),
         shape = MaterialTheme.shapes.medium,
         elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
     ) {
         Box (
             modifier = Modifier.fillMaxSize()
         ){
             Image(
                 painter = painterResource(R.drawable.news),
                 contentDescription = "News picture",
                 contentScale = ContentScale.Crop,
                 modifier = Modifier.fillMaxSize()
             )

             Box (modifier = Modifier.fillMaxSize()
                 .background(Brush.verticalGradient(
                     colors = listOf(
                         Color.Transparent,
                         Color.Black
                     ),
                     startY = 300f

                 ))
             )



            Box (
                modifier = Modifier.padding(16.dp)
                    .fillMaxSize(),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(
                    text = "News ... ",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
         }
     }
}

@Composable
fun DonorCard(donor: Donor) {

    Card(
        modifier = Modifier
            .width(120.dp)
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Profile Picture with Coil
            AsyncImage(
                model = donor.profilePicture,
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(8.dp))

            // Name and Blood Type
            Column {
                Text(
                    text = donor.name,
                    style = MaterialTheme.typography.titleMedium ,
                    maxLines = 1, // Limit to one line
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "${donor.bloodGroup}${donor.Rh}",
                    style = MaterialTheme.typography.bodyMedium ,
                    maxLines = 1, // Limit to one line
                    overflow = TextOverflow.Ellipsis,
                   // textAlign = TextAlign.Center


                    )
            }
        }
    }
}

@Composable
fun SearchBar(
    queryValue : String ,
    onQueryValueChange : (String) -> Unit ,
    modifier: Modifier
){
    TextField(
        value = queryValue,
        onValueChange = onQueryValueChange,
        placeholder = {Text("Enter Donor's name .... ")},
        singleLine = true ,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface
        )
    )
}