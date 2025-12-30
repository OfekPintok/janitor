package com.ofekpintok.janitor.features.janitor.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ofekpintok.janitor.ui.theme.JanitorTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JanitorBottomBar(
    input: String,
    onValueChanged: (String) -> Unit,
    isError: Boolean,
    onAddClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.navigationBarsPadding().imePadding(),
        tonalElevation = 2.dp,
        shadowElevation = 2.dp
    ) {
        Row(Modifier.fillMaxWidth().padding(all = 2.dp).padding(top = 8.dp), verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = input,
                onValueChange = onValueChanged,
                singleLine = true,
                isError = isError,
                supportingText = {
                    if (isError) {
                        Text(
                            text = "Enter valid input (1.01 - 3.00)",
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },
                placeholder = { Text("Weight (KG)") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = { onAddClicked() })
            )
            IconButton(
                onClick = onAddClicked,
                Modifier.padding(bottom = 12.dp),
                enabled = !isError
            ) {
                Icon(Icons.AutoMirrored.Filled.Send, contentDescription = "Send")
            }
        }
    }
}

@Preview
@Composable
private fun JanitorBottomBarPreview() {
    JanitorTheme {
        JanitorBottomBar("1.1", isError = false, onValueChanged = {}, onAddClicked = {})
    }
}

@Preview
@Composable
private fun JanitorBottomBarErrorPreview() {
    JanitorTheme {
        JanitorBottomBar("0.1", isError = true, onValueChanged = {}, onAddClicked = {})
    }
}