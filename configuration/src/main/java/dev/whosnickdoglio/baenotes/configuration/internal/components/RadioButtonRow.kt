// Copyright (C) 2025 Nicholas Doglio
// SPDX-License-Identifier: MIT

package dev.whosnickdoglio.baenotes.configuration.internal.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.whosnickdoglio.baenotes.configuration.internal.theme.BaeNotesTheme

@Composable
internal fun RadioButtonRow(
    isSelected: Boolean,
    title: String,
    onSelected: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier =
            modifier.selectable(
                selected = isSelected,
                role = Role.RadioButton,
                onClick = onSelected,
            )
    ) {
        RadioButton(selected = isSelected, onClick = null)
        Text(text = title)
    }
}

@Preview
@Composable
private fun RadioButtonRowPreview() {
    var selected by remember { mutableStateOf(false) }
    BaeNotesTheme {
        Surface(modifier = Modifier.padding(20.dp)) {
            RadioButtonRow(
                isSelected = selected,
                title = "My button",
                onSelected = { selected = !selected },
            )
        }
    }
}
