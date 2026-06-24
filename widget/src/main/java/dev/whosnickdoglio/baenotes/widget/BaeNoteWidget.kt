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
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.createGraph

@Inject
public class BaeNoteWidget(glanceStateDefinition: BaeNoteWidgetStateDefinition) :
    GlanceAppWidget() {

    override val stateDefinition: GlanceStateDefinition<*> = glanceStateDefinition

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
    private val graph: WidgetDependencyGraph
        get() = createGraph<WidgetDependencyGraph>()

    override val glanceAppWidget: GlanceAppWidget = graph.widget
}
