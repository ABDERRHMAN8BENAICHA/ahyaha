package com.example.ahyaha.presentation.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ahyaha.R
//اخرى الفعليات
@Composable
fun Events() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 8.dp)
            ) {
                val images = listOf(
                    R.drawable.imag_1,
                    R.drawable.imag_3,
                    R.drawable.imag_0,
                )

                items(images) { image ->
                    Image(
                        painter = painterResource(id = image),
                        contentDescription = "Blood donation",
                        modifier = Modifier
                            .width(200.dp)
                            .height(150.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Column(modifier = Modifier.padding(8.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween, // توزيع العناصر بشكل متباعد
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Recent Events",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .padding(bottom = 4.dp)
                            .weight(1f),
                        textAlign = TextAlign.Start
                    )

                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Play Icon",
                        modifier = Modifier.size(24.dp)
                    )
                }


                Text(
                    text = "Do some actions",
                    fontSize = 14.sp,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start
                )

            }
        }
    }
}

