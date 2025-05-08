package com.example.ahyaha.presentation.view.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.ahyaha.R
import com.example.ahyaha.data.model.Donor
import com.example.ahyaha.ui.theme.*


//معلومات النتبرعين
@Composable
fun RegularDonorsSection(donors: List<Donor>) {
    Column(modifier = Modifier.padding(16.dp)) {
        // عنوان القسم مع أيقونة السهم
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Regular Donors",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(end = 4.dp)
            )

            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Arrow Icon",
                modifier = Modifier.size(20.dp)
            )
        }

        LazyRow {
            items(donors ?: emptyList()) { donor ->
                DonorCard(donor)
            }
        }
    }
}


@Composable
fun DonorCard(donor: Donor) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        Card(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp,
                pressedElevation = 8.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = wait
            ),
            border = BorderStroke(1.dp, BloodRed),
            modifier = Modifier
                .padding(8.dp)
                .shadow(
                    elevation = 2.dp,
                    spotColor = BloodRed.copy(alpha = 0.35f)
                )
        ) {
            Column(
                modifier = Modifier.padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Profile picture with border
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(PlasmaOrange.copy(alpha = 0.1f))
                        .padding(4.dp)
                ) {
                    AsyncImage(
                        model = donor.profilePicture ?: R.drawable.ic_profile_placeholder,
                        contentDescription = "Profile picture of ${donor.name}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Donor information
                donor.name?.let { 
                    Text(
                        text = it, 
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        color = SteelGray
                    )
                }
                
                Text(
                    text = "${donor.bloodGroup}${donor.rh}",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = BloodRed,
                        fontWeight = FontWeight.Bold
                    ),
                    textAlign = TextAlign.Center
                )
                
                donor.location?.let { 
                    Text(
                        text = it, 
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = SteelGray
                        ),
                        textAlign = TextAlign.Center,
                        maxLines = 1
                    )
                }
            }
        }
    }
}