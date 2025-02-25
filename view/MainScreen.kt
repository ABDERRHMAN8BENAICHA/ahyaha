package com.example.ahyaha.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ahyaha.ui.theme.AhyahaTheme

@Composable
fun MainScreen(donorViewModel: DonorViewModel, onDonorClick: (Donor) -> Unit) {
    val uiState by donorViewModel.uiState.collectAsState()
    var searchQuery by remember { mutableStateOf("") }
    Column {
        // ✅ إضافة الصورة في الأعلى
        ImageSection()

        // ✅ شريط البحث
        SearchBar(
            query = searchQuery,
            onQueryChange = {
                searchQuery = it
                donorViewModel.filterDonorsByName(it)
            }
        )

        // ✅ قائمة المتبرعين
        LazyColumn {
            items(uiState.donors) { donor ->
                DonorItem(donor)
            }
        }
        DonorList(donors = uiState.donors, onDonorClick = onDonorClick)
        // ✅ عرض قسم الأنشطة
        ActivitySection()
        // ✅ عرض قسم "آخر المنشورات"
        RecentPostsSection()
    }
    Scaffold(
        topBar = { TopBar() },
        bottomBar = { BottomNavigationBar() }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            BloodTypeSection()
            ImageSection()
            RegularDonorsSection()
            ActivitySection()
            RecentPostsSection()
        }
    }

    @Composable
    fun DonorItem(donor: Donor, onClick: () -> Unit) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable { onClick() }
                .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(12.dp))
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // ✅ صورة المتبرع
            AsyncImage(
                model = donor.profilePicture,
                contentDescription = "Donor Image",
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(8.dp))

            // ✅ معلومات المتبرع
            Column {
                Text(
                    text = donor.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Blood Type: ${donor.bloodGroup}${donor.Rh}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }
        }
    }
    @Composable
    fun DonorList(donors: List<Donor>, onDonorClick: (Donor) -> Unit) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(donors) { donor ->
                DonorItem(donor = donor, onClick = { onDonorClick(donor) })
            }
        }
    }
    @Composable
    fun ActivitySection() {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = "Activity As",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ActivityItem(
                    icon = Icons.Default.Favorite,
                    title = "Blood Donor",
                    posts = "120",
                    onClick = { /* Navigate to Blood Donor Activity */ }
                )
                ActivityItem(
                    icon = Icons.Default.WaterDrop,
                    title = "Blood Recipient",
                    posts = "75",
                    onClick = { /* Navigate to Blood Recipient Activity */ }
                )
                ActivityItem(
                    icon = Icons.Default.Star,
                    title = "Great Post",
                    posts = "98",
                    onClick = { /* Navigate to Great Post Activity */ }
                )
            }
        }
    }

    @Composable
    fun ActivityItem(icon: ImageVector, title: String, posts: String, onClick: () -> Unit) {
        Column(
            modifier = Modifier
                .width(100.dp)
                .clickable { onClick() }
                .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(12.dp))
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                modifier = Modifier.size(40.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "$posts posts",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }
    }
    @Composable
    fun RecentPostsSection() {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = "Recent Posts",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
            )

            LazyColumn {
                items(listOf(
                    Triple("John Doe", "Just donated blood today! Feeling great! 💪", "https://images.unsplash.com/photo-1526256262350-7da7584cf5eb"),
                    Triple("Jane Smith", "Blood donation saves lives! Join the cause. ❤️", null),
                    Triple("Michael Johnson", "Here's a picture from our latest donation drive! 📸", "https://images.unsplash.com/photo-1517423440428-a5a00ad493e8")
                )) { (username, content, imageUrl) ->
                    PostItem(username = username, content = content, imageUrl = imageUrl)
                }
            }
        }
    }

    @Composable
    fun PostItem(username: String, content: String, imageUrl: String? = null) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                // ✅ اسم المستخدم
                Text(
                    text = username,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                // ✅ محتوى المنشور
                Text(
                    text = content,
                    style = MaterialTheme.typography.bodyMedium
                )

                // ✅ صورة المنشور (إذا وُجدت)
                if (imageUrl != null) {
                    Spacer(modifier = Modifier.height(8.dp))
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = "Post Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .clip(RoundedCornerShape(12.dp)),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // ✅ أيقونات الإعجاب والتعليق
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = { /* Handle Like */ }) {
                        Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = "Like")
                    }
                    IconButton(onClick = { /* Handle Comment */ }) {
                        Icon(imageVector = Icons.Default.Comment, contentDescription = "Comment")
                    }
                }
            }
        }
    }

    @Composable
    fun ImageSection(
        imageUrl: String = "https://source.unsplash.com/800x400/?blood,donation",
        modifier: Modifier = Modifier
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(8.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surface)
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = "Donation Image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }

    @Composable
    fun SearchBar(
        query: String,
        onQueryChange: (String) -> Unit,
        modifier: Modifier = Modifier
    ) {
        TextField(
            value = query,
            onValueChange = { onQueryChange(it) },
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp),
            placeholder = { Text("ابحث عن متبرع...") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "بحث") },
            singleLine = true
        )
    }

    @Composable
    fun BloodTypeSection(
        onBloodTypeSelected: (String) -> Unit
    ) {
        val bloodTypes = listOf("A+", "B+", "O+", "AB+", "A-", "B-", "O-", "AB-")

        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Section title", fontSize = 18.sp, fontWeight = FontWeight.Bold)

            LazyRow(modifier = Modifier.padding(top = 8.dp)) {
                items(bloodTypes) { bloodType ->
                    BloodTypeItem(bloodType) {
                        onBloodTypeSelected(bloodType)
                    }
                }
            }
        }
    }

    @Composable
    fun BloodTypeItem(bloodType: String, onClick: () -> Unit) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(8.dp)
                .clickable(onClick = onClick)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(Color.Red)
            ) {
                Text(text = bloodType, color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun TopBar() {
    TopAppBar(
        title = { Text("Ali Yamani", fontSize = 20.sp) },
        actions = {
            IconButton(onClick = { /* TODO: Add settings */ }) {
                Icon(imageVector = Icons.Default.Settings, contentDescription = "Settings")
            }
        },
        backgroundColor = Color.White,
        contentColor = Color.Black
    )
}

@Composable
fun BottomNavigationBar() {
    BottomNavigation(backgroundColor = Color.White) {
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            selected = true,
            onClick = { }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.LocationOn, contentDescription = "Map") },
            selected = false,
            onClick = { }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
            selected = false,
            onClick = { }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    AhyahaTheme {
        MainScreen()
    }
}
