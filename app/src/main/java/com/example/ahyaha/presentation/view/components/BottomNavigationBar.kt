package com.example.ahyaha.presentation.view.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
//ايقونات (الخريطة + الرئسية + البحث)
@Composable
fun BottomNavigationBar(onTabSelected: (Int) -> Unit) {
    BottomAppBar(

        contentColor = MaterialTheme.colorScheme.onBackground,
        tonalElevation = 0.dp
    ) {
        IconButton(onClick = { onTabSelected(0) }) {
            Icon(
                Icons.Default.LocationOn,
                contentDescription = "location",
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        IconButton(onClick = { onTabSelected(1) }) {
            Icon(
                Icons.Default.AddCircle,
                contentDescription = "addDonnor",
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        IconButton(onClick = { onTabSelected(2) }) {
            Icon(
                Icons.Default.Home,
                contentDescription = "Home",
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        IconButton(onClick = { }) {
            Icon(
                Icons.Default.List,
                contentDescription = "list",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}