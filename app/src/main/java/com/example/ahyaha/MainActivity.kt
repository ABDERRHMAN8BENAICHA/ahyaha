package com.example.ahyaha

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.ahyaha.ui.FirstUI
import com.example.ahyaha.theme.AhyahaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AhyahaTheme {
                Scaffold { innerPadding ->
                    FirstUI(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
