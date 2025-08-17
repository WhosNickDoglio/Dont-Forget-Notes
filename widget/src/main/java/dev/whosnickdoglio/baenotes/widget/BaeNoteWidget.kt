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
            GlanceAppWidgetManager(context).setWidgetPreviews(Receiver::class)
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
