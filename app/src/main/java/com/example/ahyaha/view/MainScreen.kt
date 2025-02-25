package com.example.ahyaha.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.ahyaha.R
import com.example.ahyaha.model.Donor
import com.example.ahyaha.viewmodel.DonorViewModel
import com.example.ahyaha.viewmodel.BloodTypeViewModel


@Composable
fun MainScreen(donorViewModel: DonorViewModel,bloodTypeViewModel : BloodTypeViewModel) {
    var searchQuery by remember { mutableStateOf("") }
    val donorState by donorViewModel.uiState.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // 🔍 Search Bar
        SearchBar(searchQuery) { searchQuery = it }

        Spacer(modifier = Modifier.height(16.dp))

        // 🩸 Blood Type Section
        BloodTypeSection()

        Spacer(modifier = Modifier.height(16.dp))

        // 👤 Donors List
        DonorList(donors = donorState.donors.filter {
            it.name.contains(searchQuery, ignoreCase = true) ||
                    it.bloodGroup.contains(searchQuery, ignoreCase = true)
        })

        Spacer(modifier = Modifier.height(16.dp))

        // 📌 Activity Section
        ActivitySection()

        Spacer(modifier = Modifier.height(16.dp))

        // 📰 Recent Posts
        RecentPostSection()
    }
}

class bloodTypeViewModel {

}

// 🔍 Search Bar
@Composable
fun SearchBar(searchQuery: String, onSearch: (String) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().background(Color.LightGray, CircleShape).padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Default.Search, contentDescription = "Search")
        Spacer(modifier = Modifier.width(8.dp))
        BasicTextField(
            value = searchQuery,
            onValueChange = onSearch,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
    }
}

// 🩸 Blood Type Section
@Composable
fun BloodTypeSection() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
        listOf("A+", "B+", "O+", "A-").forEach { type ->
            BloodTypeItem(type)
        }
    }
}

@Composable
fun BloodTypeItem(type: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(painter = painterResource(id = R.drawable.blood_drop), contentDescription = null, tint = Color.Red)
        Text(text = type, fontSize = 16.sp)
    }
}

// 👤 Donor List
@Composable
fun DonorList(donors: List<Donor>) {
    LazyColumn(modifier = Modifier.fillMaxHeight(0.4f)) {
        items(donors) { donor ->
            DonorCard(donor)
        }
    }
}

@Composable
fun DonorCard(donor: Donor) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = donor.profilePicture,
                contentDescription = "Profile Picture",
                modifier = Modifier.size(48.dp).clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = donor.name, fontSize = 18.sp)
                Text(text = "${donor.bloodGroup}${donor.Rh}", fontSize = 14.sp)
            }
        }
    }
}

// 📌 Activity Section
@Composable
fun ActivitySection() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Activity As", fontSize = 18.sp)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            ActivityItem("Blood Donor", R.drawable.activity_blood_donor)
            ActivityItem("Blood Recipient", R.drawable.activity_blood_recipient)
            ActivityItem("Great Post", R.drawable.activity_great_post)
        }
    }
}

@Composable
fun ActivityItem(name: String, imageRes: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = imageRes), contentDescription = null, modifier = Modifier.size(50.dp))
        Text(text = name, fontSize = 14.sp)
    }
}

// 📰 Recent Posts
@Composable
fun RecentPostSection() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Recent Post", fontSize = 18.sp)
        PostItem(R.drawable.post1)
        PostItem(R.drawable.post2)
    }
}

@Composable
fun PostItem(imageRes: Int) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Image(painter = painterResource(id = imageRes), contentDescription = null, modifier = Modifier.fillMaxWidth())
    }
}
