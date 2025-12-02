package com.example.kantin

import KantinTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KantinTheme { // Sesuaikan dengan nama tema project Anda
                KantinNavigation()
            }
        }
    }
}
