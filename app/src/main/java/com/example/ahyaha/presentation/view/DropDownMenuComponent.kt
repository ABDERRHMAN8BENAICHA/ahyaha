

package com.example.ahyaha.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun DropdownMenuComponent(
    label: String,
    selectedItem: String,
    items: List<String>,
    onItemSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(selectedItem) }

    Column {
        Text(label, style = MaterialTheme.typography.bodyLarge)
        Box {
            OutlinedTextField(
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = true }
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                items.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(item) },
                        onClick = {
                            selectedText = item
                            expanded = false
                            onItemSelected(item)
                        }
                    )
                }
            }
        }
    }
}