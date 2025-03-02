package com.example.ahyaha.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.ahyaha.R
import com.example.ahyaha.model.BloodType
import com.example.ahyaha.model.Donor
import com.example.ahyaha.repository.BloodTypeRepository
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

    Column(modifier = modifier.fillMaxSize().padding(1.dp)) {
        // 🔍 Header with Icons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.AccountCircle, // أيقونة الحساب الشخصي
                    contentDescription = "Profile Icon",
                    modifier = Modifier
                        .size(24.dp) // حجم الأيقونة
                        .padding(bottom = 4.dp), // مسافة بين الأيقونة والنص
                    tint = Color.DarkGray // لون الأيقونة (يمكن تغييره حسب التصميم)
                )


            }

            Row {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "Search",
                    modifier = Modifier.clickable { })
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    Icons.Default.Notifications,
                    contentDescription = "Notifications",
                    modifier = Modifier.clickable { })
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    Icons.Default.Settings,
                    contentDescription = "Settings",
                    modifier = Modifier.clickable { })
            }
        }


        // 🩸 Blood Type Section
        Text(
            text = " : Blood Type  ",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp)
        )
        LazyRow(modifier = Modifier.padding(vertical = 1.dp)) {
            items(bloodTypeState.bloodTypes) { bloodType ->
                BloodTypeCard(
                    bloodType = bloodType,
                    imageResId = R.drawable.blood_icon // استخدم الصورة المناسبة
                )
            }
        }
// 🖼 Donation Image Section
        SectionTitle(" : Section title ")
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp) // زيادة الارتفاع لاستيعاب العناوين
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp) // إضافة مسافة بين الصور
        ) {
            item {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_11),
                        contentDescription = "Donation Process 1",
                        modifier = Modifier
                            .width(180.dp) // زيادة عرض الصورة
                            .clip(MaterialTheme.shapes.medium),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = "title",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
            item {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_12),
                        contentDescription = "Donation Process 2",
                        modifier = Modifier
                            .width(180.dp) // زيادة عرض الصورة
                            .clip(MaterialTheme.shapes.medium),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = "title",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
            item {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_13),
                        contentDescription = "Donation Process 3",
                        modifier = Modifier
                            .width(180.dp) // زيادة عرض الصورة
                            .clip(MaterialTheme.shapes.medium),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = "title",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }

        // 💖 Regular Donors
        SectionTitle(" : Regular Donors ")
        LazyRow(modifier = Modifier.padding(vertical = 4.dp)) {
            items(donorState.donors) { donor ->
                RegularDonorCard(donor = donor)
            }
        }
        // 🔥 Activity Section
        SectionTitle(" :Activity ")
        LazyRow(modifier = Modifier.padding(vertical = 1.dp)) {
            items(
                listOf("Blood Donor", "Blood Recipient", "Great Post").zip(
                    listOf(
                        listOf(R.drawable.img_8),
                        listOf(R.drawable.img_9),
                        listOf(R.drawable.img_10)
                    )
                )
            ) { (activity, images) ->
                ActivityCard(activityName = activity, imageResIds = images)
            }
        }

        // 📰 Recent Posts
        SectionTitle(" :Recent Post ")
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(
                listOf(
                    Triple(
                        "Headline",
                        "Today - 23/01/2025",
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRigJUsE-KJ75c9WUUMHTTAgXOH3fHsOpKA_w&usqp=CAU"
                    ),
                    Triple(
                        "Headline",
                        "Yesterday - 22/01/2025",
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQDBE5AHBbiWaJD6X0r9OIYn_WQQlGfM1d3XQ&s"
                    )
                )
            ) { (title, date, imageUrl) ->
                PostCard(
                    title = title,
                    date = date,
                    imageUrl = imageUrl

                )
            }
        }


        var selectedTab by remember { mutableStateOf(0) }

        Column {
            // عرض المحتوى بناءً على التبويب المحدد
            Text(text = "المحتوى الحالي للتبويب $selectedTab")

            // شريط التنقل السفلي
            BottomNavigationBar(
                onTabSelected = { selectedTab = it },
                selectedTab = selectedTab
            )
        }
    }


}



@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(start = 5.dp, top = 5.dp, bottom = 1.dp)
    )
}
@Composable
fun BloodTypeCard(bloodType: BloodType, imageResId: Int) {
    Card(
        modifier = Modifier
            .padding(6.dp)
            .size(60.dp),
        shape = CircleShape,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        border = BorderStroke(0.5.dp, Color.Red)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = bloodType.Picture), // ✅ استخدام picture بدلاً من Picture
                    contentDescription = "Blood Type ${bloodType.bloodGroup}${bloodType.Rh}",
                    modifier = Modifier
                        .size(45.dp)
                        .padding(4.dp)
                )
                Text(
                    text = "${bloodType.Rh}${bloodType.bloodGroup}",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.Red
                )
            }
        }
    }
}

@Composable
fun BloodTypeGrid() {
    val bloodTypes = BloodTypeRepository.getAllBloodTypes() // ✅ جلب الفصائل

    Column {
        bloodTypes.chunked(4).forEach { rowBloodTypes -> // ✅ تقسيم العناصر إلى صفوف
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                rowBloodTypes.forEach { bloodType ->
                    BloodTypeCard(bloodType = bloodType, imageResId = R.drawable.blood_icon)
                }
            }
        }
    }
}




@Composable
fun RegularDonorCard(donor: Donor) {
    Column(
        modifier = Modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(donor.profilePicture),
            contentDescription = "Profile Picture",
            modifier = Modifier.size(50.dp).clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Text(text = donor.name, style = MaterialTheme.typography.bodySmall)
    }
}
@Composable
fun ActivityCard(
    activityName: String,
    imageResIds: List<Int> // ✅ استلام قائمة من الصور
) {
    Card(
        modifier = Modifier
            .padding(2.dp)
            .size(100.dp),
        shape = RoundedCornerShape(6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // ✅ عرض الصور الثلاثة في صف أفقي
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                imageResIds.forEach { imageResId ->
                    Image(
                        painter = painterResource(id = imageResId),
                        contentDescription = "Activity Image",
                        modifier = Modifier
                            .size(50.dp) // ✅ تعديل حجم الصور
                            .padding(2.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(0.1.dp))

            // ✅ اسم النشاط
            Text(
                text = activityName,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
@Composable
fun PostCard(title: String, date: String, imageUrl: String) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = rememberAsyncImagePainter(imageUrl),
                contentDescription = title,
                modifier = Modifier.size(64.dp).clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(text = title, style = MaterialTheme.typography.titleMedium)
                Text(text = date, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}
@Composable
fun BottomNavigationBar(onTabSelected: (Int) -> Unit, selectedTab: Int) {
    BottomAppBar(
        contentColor = MaterialTheme.colorScheme.onBackground,
        tonalElevation = 0.dp
    ) {
        IconButton(onClick = { onTabSelected(1) }) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "الخريطة",
                modifier = Modifier.size(24.dp),
                tint = if (selectedTab == 1) Color.Blue else Color.Black // تغيير اللون عند التحديد
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        IconButton(onClick = { onTabSelected(0) }) {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "الرئيسية",
                modifier = Modifier.size(24.dp),
                tint = if (selectedTab == 0) Color.Blue else Color.Black // تغيير اللون عند التحديد
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        IconButton(onClick = { onTabSelected(2) }) { // تم إضافة تحديد رقم التبويب
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "بحث",
                modifier = Modifier.size(24.dp),
                tint = if (selectedTab == 2) Color.Blue else Color.Black // تغيير اللون عند التحديد
            )
        }
    }
}
