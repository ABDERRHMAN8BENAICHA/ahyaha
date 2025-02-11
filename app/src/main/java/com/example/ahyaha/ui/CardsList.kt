package com.example.ahyaha.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ahyaha.theme.CoolGray
import com.example.ahyaha.theme.PulseRed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete


@Composable
fun CardsList(displayedItems: List<String>, onDeleteItem: (String) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(displayedItems) { item ->
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = item,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontSize = 18.sp,
                            color = CoolGray
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    IconButton(
                        onClick = { onDeleteItem(item) },
                        colors = IconButtonDefaults.iconButtonColors(containerColor = PulseRed)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete",
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}
