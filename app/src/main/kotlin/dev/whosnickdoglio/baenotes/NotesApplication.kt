// Copyright (C) 2026 Nicholas Doglio
// SPDX-License-Identifier: MIT

package dev.whosnickdoglio.baenotes

import android.app.Application
import dev.whosnickdoglio.baenotes.di.AppDependencyGraph
import dev.whosnickdoglio.baenotes.graph.GraphOwner
import dev.zacsweers.metro.createGraph
import dev.zacsweers.metrox.android.MetroAppComponentProviders
import dev.zacsweers.metrox.android.MetroApplication

public class NotesApplication : Application(), MetroApplication, GraphOwner {

    override val graph: AppDependencyGraph by lazy {
        createGraph()
    }
    override val appComponentProviders: MetroAppComponentProviders
        get() = graph
}
