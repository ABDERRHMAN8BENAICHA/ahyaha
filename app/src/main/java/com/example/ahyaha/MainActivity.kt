package com.example.ahyaha

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.clickable
import androidx.compose.ui.Alignment
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import com.example.ahyaha.ui.theme.AhyahaTheme // ✅ Ensure this import is correct

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AhyahaTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    FirstUI()
                }
            }
        }
    }
}

@Composable
fun FirstUI() {
    var textValue by remember { mutableStateOf("") }
    val allItems = remember { mutableStateListOf<String>() }
    var searchQuery by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    val displayedItems = if (searchQuery.isEmpty()) {
        allItems
    } else {
        allItems.filter { it.contains(searchQuery, ignoreCase = true) }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        SearchInputBar(
            textValue = textValue,
            onTextChange = { textValue = it },
            onAddClick = {
                if (textValue.isNotBlank()) {
                    allItems.add(textValue)
                    textValue = ""
                    errorMessage = ""
                } else {
                    errorMessage = "Input cannot be empty"
                }
            },
            onSearchClick = {
                searchQuery = textValue
            }
        )
        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(8.dp))
        }
        CardsList(displayedItems) { item ->
            allItems.remove(item)
        }
    }
}

@Composable
fun SearchInputBar(
    textValue: String,
    onTextChange: (String) -> Unit,
    onAddClick: () -> Unit,
    onSearchClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = textValue,
            onValueChange = onTextChange,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Enter item") },
            singleLine = true
        )
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = onAddClick) { Text("Add") }
            Button(onClick = onSearchClick) { Text("Search") }
        }
    }
}
@Composable
fun CardsList(items: List<String>, onDelete: (String) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize().padding(top = 16.dp)) {
        if (items.isEmpty()) {
            item {
                Text("No results found", modifier = Modifier.padding(16.dp), color = MaterialTheme.colorScheme.error)
            }
        }
        items(items) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable { onDelete(item) },
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = item, modifier = Modifier.weight(1f))
                    IconButton(onClick = { onDelete(item) }) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AhyahaTheme {
        FirstUI()
    }
}