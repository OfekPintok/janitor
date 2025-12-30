package com.ofekpintok.janitor.features.janitor.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ofekpintok.janitor.features.janitor.ui.viewmodel.JanitorViewModel

@Composable
fun JanitorScreen(viewModel: JanitorViewModel) {
    val bagsState by viewModel.bagsState.collectAsStateWithLifecycle()
    val tripsState by viewModel.tripsState.collectAsStateWithLifecycle()

    var weightInput by rememberSaveable { mutableStateOf("") }
    val isError = viewModel.isOutOfRange(weightInput)

    Scaffold(
        topBar = { JanitorTopBar(onRefreshClicked = { viewModel.resetJanitor() }) },
        bottomBar = {
            JanitorBottomBar(
                weightInput,
                isError = isError,
                onValueChanged = { newInput ->
                    val validChars = newInput.all { it.isDigit() || it == '.' }
                    val singleDot = newInput.count { it == '.' } <= 1

                    if (validChars && singleDot) {
                        weightInput = newInput
                    }
                },
                onAddClicked = {
                    if (!isError) {
                        weightInput.toDoubleOrNull()?.let { viewModel.addBag(it) }
                        weightInput = ""
                    }
                }
            )
        },
        content = { paddingValues ->
            JanitorContent(
                bags = bagsState,
                trips = tripsState,
                modifier = Modifier.padding(paddingValues)
            )
        })
}