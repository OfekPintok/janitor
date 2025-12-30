package com.ofekpintok.janitor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.ofekpintok.janitor.features.janitor.domain.CalculateTripsUseCase
import com.ofekpintok.janitor.features.janitor.ui.JanitorScreen
import com.ofekpintok.janitor.features.janitor.ui.viewmodel.JanitorViewModel
import com.ofekpintok.janitor.ui.theme.JanitorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val janitorViewModel: JanitorViewModel = viewModel(
                factory = viewModelFactory {
                    initializer {
                        JanitorViewModel(
                            savedStateHandle = createSavedStateHandle(),
                            calculateTripsUseCase = CalculateTripsUseCase()
                        )
                    }
                }
            )

            JanitorTheme {
                JanitorScreen(janitorViewModel)
            }
        }
    }
}