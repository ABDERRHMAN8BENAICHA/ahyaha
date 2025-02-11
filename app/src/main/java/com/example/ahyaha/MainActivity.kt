import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListManagementApp()
        }
    }
}

@Composable
fun ListManagementApp() {
    var textValue by remember { mutableStateOf("") }
    val allItems = remember { mutableStateListOf<String>() }
    var searchQuery by remember { mutableStateOf("") }

    // عناصر التصفية
    val displayedItems = if (searchQuery.isEmpty()) {
        allItems
    } else {
        allItems.filter { it.contains(searchQuery, ignoreCase = true) }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // إدخال نص وقائمة بحث
        SearchInputBar(
            textValue = textValue,
            onTextChange = { textValue = it },
            onAddClick = {
                if (textValue.isNotBlank()) {
                    allItems.add(textValue.trim())
                    textValue = ""
                }
            },
            onSearchClick = { searchQuery = textValue.trim() }
        )

        Spacer(modifier = Modifier.height(12.dp))

        // عرض العناصر
        if (displayedItems.isEmpty()) {
            Text(
                "لا توجد نتائج مطابقة.",
                color = Color.Gray,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(displayedItems) { item ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(Color(0xFFF1F1F1))
                    ) {
                        Text(
                            text = item,
                            modifier = Modifier.padding(16.dp),
                            color = Color.Black
                        )
                    }
                }
            }
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
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TextField(
                value = textValue,
                onValueChange = onTextChange,
                label = { Text("أدخل نصًا") },
                modifier = Modifier.weight(1f)
            )
            Button(onClick = onAddClick) {
                Text("إضافة")
            }
            Button(onClick = onSearchClick) {
                Text("بحث")
            }
        }
    }
}
