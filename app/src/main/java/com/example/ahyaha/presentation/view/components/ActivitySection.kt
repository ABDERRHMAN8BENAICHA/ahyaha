package com.example.ahyaha.presentation.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ahyaha.R
//قسم اخرى النشطات
// تحديث البيانات لتشمل الصورة لكل عنصر
data class ActivityItem(val title: String, val subtitle: String, val imageRes: Int)

@Composable
fun ActivitySection() {
    Column(modifier = Modifier.padding(8.dp)) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Activity As",
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

        // قائمة أفقية للعناصر مع صور خاصة بكل عنصر
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 8.dp)
        ) {
            val activities = listOf(
                ActivityItem("Blood Recipient", "120 posts", R.drawable.blood_3),
                ActivityItem("Blood Donor", "130 posts, Stay healthy", R.drawable.blood_1),
                ActivityItem("Create Post", "One Step to Go! Super Easy", R.drawable.blood_2)

            )

            items(activities) { activity ->
                ActivityCard(activity)
            }
        }
    }
}

@Composable
fun ActivityCard(activity: ActivityItem) {
    Card(
        modifier = Modifier
            .width(120.dp)
            .padding(4.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {
            // صورة خاصة بكل عنصر
            Image(
                painter = painterResource(id = activity.imageRes),
                contentDescription = activity.title,
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.height(4.dp))


            Text(
                text = activity.title,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )

            Text(
                text = activity.subtitle,
                fontSize = 12.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        }
    }
}
