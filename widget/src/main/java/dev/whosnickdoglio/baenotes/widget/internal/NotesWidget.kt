// Copyright (C) 2025 Nicholas Doglio
// SPDX-License-Identifier: MIT

package dev.whosnickdoglio.baenotes.widget.internal

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.background
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.preview.ExperimentalGlancePreviewApi
import androidx.glance.preview.Preview
import androidx.glance.semantics.contentDescription
import androidx.glance.semantics.semantics
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import dev.whosnickdoglio.baenotes.model.NoteColor
import dev.whosnickdoglio.baenotes.model.NoteWidgetState

internal const val NoteTextSemantics = "Note content"

@Composable
internal fun NoteWidget(state: NoteWidgetState, modifier: GlanceModifier = GlanceModifier) {
    LazyColumn(
        modifier =
        modifier
            .fillMaxSize()
            .background(
                state.backgroundColor.toComposeColor(GlanceTheme.colors.widgetBackground)
            )
    ) {
        item {
            Text(
                text = state.content,
                modifier = GlanceModifier.padding(12.dp)
                    .semantics { contentDescription = NoteTextSemantics },
                style =
                TextStyle(
                    fontSize = state.textSize.sp,
                    color =
                    state.textColor.toComposeColor(GlanceTheme.colors.onBackground)
                ),
            )
        }
    }
}

@OptIn(ExperimentalGlancePreviewApi::class)
@Preview
@Composable
private fun PreviewNoteWidget() {
    GlanceTheme {
        NoteWidget(
            state = NoteWidgetState(
                content = "Hello preview!"
            )
        )
    }
}

private fun NoteColor.toComposeColor(system: ColorProvider): ColorProvider =
    when (this) {
        NoteColor.BLACK -> ColorProvider(Color.Black)
        NoteColor.WHITE -> ColorProvider(Color.White)
        NoteColor.TRANSPARENT -> ColorProvider(Color.Transparent)
        NoteColor.PINK -> ColorProvider(Color.Magenta)
        NoteColor.SYSTEM -> system
    }
