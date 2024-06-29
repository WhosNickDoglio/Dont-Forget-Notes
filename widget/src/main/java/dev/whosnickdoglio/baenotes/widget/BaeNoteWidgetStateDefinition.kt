package dev.whosnickdoglio.baenotes.widget

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.Serializer
import androidx.datastore.dataStoreFile
import androidx.glance.state.GlanceStateDefinition
import dev.whosnickdoglio.baenotes.model.Note
import java.io.EOFException
import java.io.InputStream
import java.io.OutputStream
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

internal object BaeNoteWidgetStateDefinition : GlanceStateDefinition<Note> {

    override suspend fun getDataStore(context: Context, fileKey: String): DataStore<Note> =
        DataStoreFactory.create(
            serializer = NoteSerializer,
            produceFile = { context.dataStoreFile("bae_notes_$fileKey") })

    override fun getLocation(context: Context, fileKey: String) =
        context.dataStoreFile("bae_notes_$fileKey")
}

private object NoteSerializer : Serializer<Note> {

    override val defaultValue: Note = Note()

    override suspend fun readFrom(input: InputStream): Note =
        try {
            Json.decodeFromString(Note.serializer(), input.readBytes().decodeToString())
        } catch (exception: EOFException) {
            throw CorruptionException("Unable to read Notes", exception)
        }

    override suspend fun writeTo(t: Note, output: OutputStream) =
        withContext(Dispatchers.IO) {
            output.write(Json.encodeToString(Note.serializer(), t).encodeToByteArray())
        }
}
