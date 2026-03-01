// Copyright (C) 2025 Nicholas Doglio
// SPDX-License-Identifier: MIT

package dev.whosnickdoglio.baenotes.widget.internal

import android.util.Log
import androidx.datastore.core.Serializer
import dev.whosnickdoglio.baenotes.model.NoteWidgetState
import java.io.EOFException
import java.io.InputStream
import java.io.OutputStream
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

internal class NoteSerializer(private val dispatcher: CoroutineDispatcher = Dispatchers.IO) :
    Serializer<NoteWidgetState> {

    override val defaultValue: NoteWidgetState = NoteWidgetState()

    override suspend fun readFrom(input: InputStream): NoteWidgetState =
        withContext(dispatcher) {
            try {
                Json.decodeFromString(
                    NoteWidgetState.serializer(),
                    input.readBytes().decodeToString(),
                )
            } catch (exception: EOFException) {
                // When this is ready for the apps store we'll replace with Bugsnag?
                Log.e("NoteSerializer", "Oops", exception)
                // fallback to empty note state
                NoteWidgetState()
            }
        }

    override suspend fun writeTo(t: NoteWidgetState, output: OutputStream) =
        withContext(dispatcher) {
            output.write(Json.encodeToString(NoteWidgetState.serializer(), t).encodeToByteArray())
        }
}
