// Copyright (C) 2025 Nicholas Doglio
// SPDX-License-Identifier: MIT

package dev.whosnickdoglio.baenotes.widget

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.glance.GlanceId
import androidx.glance.GlanceTheme
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.composeForPreview
import androidx.glance.appwidget.provideContent
import androidx.glance.appwidget.setWidgetPreviews
import androidx.glance.currentState
import androidx.glance.preview.ExperimentalGlancePreviewApi
import androidx.glance.preview.Preview
import androidx.glance.state.GlanceStateDefinition
import dev.whosnickdoglio.baenotes.model.NoteColor
import dev.whosnickdoglio.baenotes.model.NoteWidgetState
import dev.whosnickdoglio.baenotes.widget.internal.BaeNoteWidgetStateDefinition
import dev.whosnickdoglio.baenotes.widget.internal.NoteWidget

public object BaeNoteWidget : GlanceAppWidget() {

    public class Receiver : GlanceAppWidgetReceiver() {
        override val glanceAppWidget: GlanceAppWidget = BaeNoteWidget
    }

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

    override suspend fun providePreview(
        context: Context,
        widgetCategory: Int
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM) {
            val result = GlanceAppWidgetManager(context).setWidgetPreviews(Receiver::class)

            when (result) {
                GlanceAppWidgetManager.SET_WIDGET_PREVIEWS_RESULT_SUCCESS -> {

                }

                GlanceAppWidgetManager.SET_WIDGET_PREVIEWS_RESULT_RATE_LIMITED -> {

                }
            }

        }
        provideContent { NoteWidgetPreview() }
    }
}

@Composable
private fun NoteWidgetPreview() {
    GlanceTheme {
        NoteWidget(
            state = NoteWidgetState(
                content = "Make Vet appointment for Wheat Thin",
                textColor = NoteColor.WHITE,
                backgroundColor = NoteColor.PINK
            )
        )
    }
}

//@OptIn(ExperimentalGlancePreviewApi::class)
//@Preview
//@Composable
//private fun NoteWidgetPreviewPreview() {
//    BaeNoteWidget.composeForPreview()
//
//}
