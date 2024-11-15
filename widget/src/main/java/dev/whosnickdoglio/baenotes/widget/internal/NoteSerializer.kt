/*
 * MIT License
 *
 * Copyright (c) 2024. Nicholas Doglio
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package dev.whosnickdoglio.baenotes.widget.internal

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import dev.whosnickdoglio.baenotes.model.NoteWidgetState
import java.io.EOFException
import java.io.InputStream
import java.io.OutputStream
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

internal class NoteSerializer(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : Serializer<NoteWidgetState> {

    override val defaultValue: NoteWidgetState = NoteWidgetState()

    override suspend fun readFrom(input: InputStream): NoteWidgetState = withContext(dispatcher) {
        try {
            Json.decodeFromString(
                NoteWidgetState.serializer(), input.readBytes().decodeToString()
            )
        } catch (exception: EOFException) {
            throw CorruptionException("Unable to read Notes", exception)
        }
    }

    override suspend fun writeTo(
        t: NoteWidgetState, output: OutputStream
    ) = withContext(dispatcher) {
        output.write(Json.encodeToString(NoteWidgetState.serializer(), t).encodeToByteArray())
    }
}
