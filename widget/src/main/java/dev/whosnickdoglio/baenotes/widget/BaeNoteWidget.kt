package dev.whosnickdoglio.baenotes.widget

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.provideContent
import androidx.glance.currentState
import androidx.glance.state.GlanceStateDefinition
import dev.whosnickdoglio.baenotes.model.Note

object BaeNoteWidget : GlanceAppWidget() {

    override val stateDefinition: GlanceStateDefinition<*> = BaeNoteWidgetStateDefinition

    override val sizeMode: SizeMode = SizeMode.Exact

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            val state = currentState<Note>()
            NoteWidget(state)
        }
    }
}

class BaeNoteWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = BaeNoteWidget
}
