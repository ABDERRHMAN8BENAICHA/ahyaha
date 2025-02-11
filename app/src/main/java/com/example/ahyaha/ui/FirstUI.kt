package com.example.ahyaha.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FirstUI(modifier: Modifier = Modifier) {
    // State variables
    var textValue by remember { mutableStateOf("") }
    val allItems = remember { mutableStateListOf<String>() }
    var searchQuery by remember { mutableStateOf("") }

    // Filtered list for search functionality
    val displayedItems = if (searchQuery.isEmpty()) {
        allItems
    } else {
        allItems.filter { it.contains(searchQuery, ignoreCase = true) }
    }

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        SearchInputBar(
            textValue = textValue,
            onTextValueChange = { textValue = it },
            onAddItem = {
                if (it.isNotBlank() && it !in allItems) {
                    allItems.add(it)
                    textValue = "" // Clear input after adding
                }
            },
            onSearch = { searchQuery = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        CardsList(displayedItems, onDeleteItem = { allItems.remove(it) })
    }
}
