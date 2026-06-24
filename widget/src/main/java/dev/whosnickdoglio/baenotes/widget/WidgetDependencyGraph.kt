// Copyright (C) 2026 Nicholas Doglio
// SPDX-License-Identifier: MIT

package dev.whosnickdoglio.baenotes.widget

import androidx.glance.appwidget.GlanceAppWidget
import dev.zacsweers.metro.DependencyGraph

@DependencyGraph(WidgetScope::class)
internal interface WidgetDependencyGraph {
    val widget: BaeNoteWidget
}


internal sealed interface WidgetScope
