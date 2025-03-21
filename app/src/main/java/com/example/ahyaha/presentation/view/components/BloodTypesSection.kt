package com.example.ahyaha.presentation.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import com.example.ahyaha.R
//ايقونات الزمرة +العنوان
@Composable
fun BloodTypesSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Blood types",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.padding(end = 4.dp)
        )

        Icon(
            imageVector = Icons.Default.ArrowForward,
            contentDescription = "Arrow",
            modifier = Modifier.size(20.dp)
        )
    }
}



@Composable
fun ImageSection() {
    val bloodTypes = listOf("A+", "AB+", "O+", "B+", "A-", "AB-", "O-", "B-")
    val icons = listOf(
        R.mipmap.group_1,
        R.mipmap.group_2,
        R.mipmap.group_3,
        R.mipmap.group_4,
        R.mipmap.group_1,
        R.mipmap.group_2,
        R.mipmap.group_3,
        R.mipmap.group_4
    )

    LazyRow(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        itemsIndexed(bloodTypes) { index, bloodType ->
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = icons[index]),
                    contentDescription = "Blood type $bloodType",
                    modifier = Modifier
                        .size(60.dp)
                        .clickable { }
                )
                Text(text = bloodType, textAlign = TextAlign.Center)
            }
        }
    }
}
