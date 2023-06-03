package dev.whosnickdoglio.baenotes.widget

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import androidx.datastore.dataStoreFile
import androidx.glance.state.GlanceStateDefinition
import dev.whosnickdoglio.baenotes.Note
import java.io.EOFException
import java.io.File
import java.io.InputStream
import java.io.OutputStream
import kotlinx.serialization.json.Json

private val Context.dataStore by dataStore(fileName = "bae_notes", serializer = NoteSerializer)

object BaeNoteWidgetStateDefinition : GlanceStateDefinition<Note> {

    override suspend fun getDataStore(context: Context, fileKey: String): DataStore<Note> =
        context.dataStore

    override fun getLocation(context: Context, fileKey: String): File =
        context.dataStoreFile("bae_notes")
}

object NoteSerializer : Serializer<Note> {

    override val defaultValue: Note = Note()

    override suspend fun readFrom(input: InputStream): Note =
        try {
            Json.decodeFromString(Note.serializer(), input.readBytes().decodeToString())
        } catch (exception: EOFException) {
            throw CorruptionException("Unable to read Notes", exception)
        }

    override suspend fun writeTo(t: Note, output: OutputStream) {
        output.write(Json.encodeToString(Note.serializer(), t).encodeToByteArray())
    }
}
