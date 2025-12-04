// Copyright (C) 2025 Nicholas Doglio
// SPDX-License-Identifier: MIT

package dev.whosnickdoglio.baenotes.widget

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.GlanceTheme
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.provideContent
import androidx.glance.currentState
import androidx.glance.state.GlanceStateDefinition
import dev.whosnickdoglio.baenotes.model.NoteWidgetState
import dev.whosnickdoglio.baenotes.widget.internal.BaeNoteWidgetStateDefinition
import dev.whosnickdoglio.baenotes.widget.internal.NoteWidget

public object BaeNoteWidget : GlanceAppWidget() {

    override val stateDefinition: GlanceStateDefinition<*> = BaeNoteWidgetStateDefinition

    override val sizeMode: SizeMode = SizeMode.Exact

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            GlanceTheme {
                val state = currentState<NoteWidgetState>()
                NoteWidget(state)
            }
        }
    }
}

public class BaeNoteWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = BaeNoteWidget
}
