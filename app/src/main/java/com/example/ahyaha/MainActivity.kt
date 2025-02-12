package com.example.ahyaha
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListApp()
        }
    }
}

@Composable
fun ListApp() {
    var textValue by remember { mutableStateOf("") }
    val allItems = remember { mutableStateListOf<String>() }
    var searchQuery by remember { mutableStateOf("") }

    val displayedItems = allItems.filter { it.contains(searchQuery, ignoreCase = true) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "AHYAHA",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            textAlign = TextAlign.Center
        )

        SearchInputBar(
            textValue = textValue,
            onTextChange = { text -> textValue = text
                searchQuery = text},
            onSearch = { searchQuery = textValue }
        )

        Spacer(modifier = Modifier.height(8.dp))

        AddButton(
            onAdd = {
                if (textValue.isNotBlank()) {
                    allItems.add(textValue)
                    textValue = ""
                    searchQuery = ""
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        CardsList(items = displayedItems, onDelete = { allItems.remove(it) })

        if (displayedItems.isEmpty()) {
            Text(
                text = "No items found",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}

@Composable
fun SearchInputBar(
    textValue: String,
    onTextChange: (String) -> Unit,
    onSearch: () -> Unit
) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        OutlinedTextField(
            value = textValue,
            onValueChange = onTextChange,
            label = { Text("Enter text") },
            modifier = Modifier.weight(1f)
        )

        Button(onClick = onSearch, modifier = Modifier.height(56.dp)) {
            Text("Search")
        }
    }
}

@Composable
fun AddButton(onAdd: () -> Unit) {
    Button(onClick = onAdd, modifier = Modifier.fillMaxWidth()) {
        Text("Add")
    }
}

@Composable
fun CardsList(items: List<String>, onDelete: (String) -> Unit) {
    LazyColumn {
        items(items) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = item)

                    IconButton(onClick = { onDelete(item) }) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewListApp() {
    ListApp()
}