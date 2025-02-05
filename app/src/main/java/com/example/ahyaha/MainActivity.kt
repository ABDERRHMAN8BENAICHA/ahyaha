package com.example.ahyaha

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ahyaha.ui.theme.AhyahaTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AhyahaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FirstUI(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}



/**
 * Main composable function for the UI layout
 * @param modifier Modifier for layout adjustments
 */
@Composable
fun FirstUI(modifier: Modifier = Modifier) {
    // TODO 1: Create state variables for text input and items list
    var item by remember { mutableStateOf("") }
    var items by remember { mutableStateOf(listOf<String>()) }
    var searchQuery by remember { mutableStateOf("") }
    var editingIndex by remember { mutableStateOf(-1) }

    Column(
        modifier = modifier
            .padding(25.dp)
            .fillMaxSize()
    ) {
        SearchInputBar(
            textValue = item, // TODO 2: Connect to state
            onTextValueChange = { /* TODO 3: Update text state */
                text ->  item = text
            },
            onAddItem = { /* TODO 4: Add item to list */
               if(item.isNotBlank()){
                 if(editingIndex != -1){
                     items = items.toMutableList().apply {
                         this[editingIndex] = item
                     }
                     editingIndex = -1
                 }else{
                     items = items + item
                 }
                   item = ""
               }
            },
            onSearch = { /* TODO 5: Implement search functionality */
                query -> searchQuery = query

            },
            isEditing = editingIndex != -1
        )


        // TODO 6: Display list of items using CardsList composable
        val displayItems = if(searchQuery.isEmpty()){
            items
        }else{
            items.filter { it.contains(searchQuery , ignoreCase = true) }
        }

        CardsList(displayedItems = displayItems,
            onEditItem = {  index , itemText ->
                item = itemText
                editingIndex = index
            },
            onDeleteItem = {index ->
                items = items.toMutableList().apply { removeAt(index) }
            }
        )
    }
}

/**
 * Composable for search and input controls
 * @param textValue Current value of the input field
 * @param onTextValueChange Callback for text changes
 * @param onAddItem Callback for adding new items
 * @param onSearch Callback for performing search
 */
@Composable
fun SearchInputBar(
    textValue: String,
    onTextValueChange: (String) -> Unit,
    onAddItem: (String) -> Unit,
    onSearch: (String) -> Unit,
    isEditing: Boolean
) {
    Column {
        TextField(
            value = textValue,
            onValueChange = onTextValueChange,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.LightGray,
                cursorColor = Color.Black
            ),
            placeholder = {
                Text("Enter text...",
                    color = Color.Black
                )
            }

        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { /* TODO 7: Handle add button click */
                onAddItem(textValue)
            }) {
                Text(if(isEditing) "Update" else "Add")
            }

            Button(onClick = { /* TODO 8: Handle search button click */
                onSearch(textValue)
            }) {
                Text("Search")
            }
        }
    }
}

/**
 * Composable for displaying a list of items in cards
 * @param displayedItems List of items to display
 */
@Composable
fun CardsList(
    displayedItems: List<String>,
    onEditItem: (Int, String) -> Unit,
    onDeleteItem: (Int) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(displayedItems.size) { index ->
            val item = displayedItems[index]
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = item, color = Color.Black)

                    Row {
                        // Edit Button
                        Button(
                            onClick = { onEditItem(index, item) },
                            modifier = Modifier.padding(end = 8.dp)
                        ) {
                            Text("Edit")
                        }

                        // Delete Button
                        Button(
                            onClick = { onDeleteItem(index) }
                        ) {
                            Text("Delete")
                        }
                    }
                }
            }
        }
    }
}