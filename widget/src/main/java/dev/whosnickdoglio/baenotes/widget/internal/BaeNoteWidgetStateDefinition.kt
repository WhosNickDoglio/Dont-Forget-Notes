// Copyright (C) 2025 Nicholas Doglio
// SPDX-License-Identifier: MIT

package dev.whosnickdoglio.baenotes.widget.internal

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.dataStoreFile
import androidx.glance.state.GlanceStateDefinition
import dev.whosnickdoglio.baenotes.model.NoteWidgetState

internal object BaeNoteWidgetStateDefinition : GlanceStateDefinition<NoteWidgetState> {

    override suspend fun getDataStore(
        context: Context,
        fileKey: String,
    ): DataStore<NoteWidgetState> =
        DataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler { NoteWidgetState() },
            serializer = NoteSerializer(),
            produceFile = { context.dataStoreFile("bae_notes_$fileKey") },
        )

    override fun getLocation(context: Context, fileKey: String) =
        context.dataStoreFile("bae_notes_$fileKey")
}
