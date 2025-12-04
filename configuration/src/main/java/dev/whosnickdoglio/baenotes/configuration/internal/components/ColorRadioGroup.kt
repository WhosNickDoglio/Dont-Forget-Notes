// Copyright (C) 2025 Nicholas Doglio
// SPDX-License-Identifier: MIT

package dev.whosnickdoglio.baenotes.configuration.internal.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.whosnickdoglio.baenotes.configuration.internal.theme.BaeNotesTheme
import dev.whosnickdoglio.baenotes.model.NoteColor
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
internal fun ColorRadioGroup(
    title: String,
    options: ImmutableList<NoteColor>,
    currentSelection: NoteColor,
    onSelected: (NoteColor) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(text = title)
        options.forEach {
            RadioButtonRow(
                isSelected = it == currentSelection,
                title = it.toString(),
                onSelected = { onSelected(it) }
            )
        }
    }
}

@Preview
@Composable
private fun ColorRadioGroupPreview() {
    var selection by remember { mutableStateOf(NoteColor.PINK) }
    BaeNotesTheme {
        Surface(modifier = Modifier.padding(20.dp)) {
            ColorRadioGroup(
                title = "Colors",
                options = persistentListOf(NoteColor.BLACK, NoteColor.WHITE, NoteColor.PINK),
                currentSelection = selection,
                onSelected = { selection = it }
            )
        }
    }
}
