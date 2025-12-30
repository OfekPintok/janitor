package com.ofekpintok.janitor.features.janitor.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ofekpintok.janitor.ui.theme.JanitorTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JanitorTopBar(onRefreshClicked: () -> Unit, modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = { Text(LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy"))) },
        actions = {
            IconButton(onRefreshClicked) {
                Icon(Icons.Default.Refresh, contentDescription = "Refresh")
            }
        }
    )
}

@Preview
@Composable
private fun JanitorTopBarPreview() {
    JanitorTheme {
        JanitorTopBar({})
    }
}