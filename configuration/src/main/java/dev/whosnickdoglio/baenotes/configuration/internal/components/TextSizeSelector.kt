// Copyright (C) 2025 Nicholas Doglio
// SPDX-License-Identifier: MIT

package dev.whosnickdoglio.baenotes.configuration.internal.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.whosnickdoglio.baenotes.configuration.internal.theme.BaeNotesTheme

@Composable
internal fun TextSizeSelector(
    textSize: String,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        Text(text = "Text Size")

        Row(verticalAlignment = Alignment.CenterVertically) {
            Button(onClick = onDecrement) { Text(text = "-") }

            Text(text = textSize, modifier = Modifier.padding(horizontal = 4.dp), fontSize = 20.sp)

            Button(onClick = onIncrement) { Text(text = "+") }
        }
    }
}

@Preview
@Composable
private fun TextSizeSelectorPreview() {
    @Suppress("MagicNumber") var textSize by remember { mutableIntStateOf(12) }

    BaeNotesTheme {
        Surface(modifier = Modifier.padding(20.dp)) {
            TextSizeSelector(
                textSize = textSize.toString(),
                onDecrement = { textSize-- },
                onIncrement = { textSize++ },
            )
        }
    }
}
