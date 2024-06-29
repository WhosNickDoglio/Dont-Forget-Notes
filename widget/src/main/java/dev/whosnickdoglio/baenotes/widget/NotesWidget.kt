package dev.whosnickdoglio.baenotes.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.background
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import dev.whosnickdoglio.baenotes.model.Color
import dev.whosnickdoglio.baenotes.model.Note

@Composable
internal fun NoteWidget(state: Note, modifier: GlanceModifier = GlanceModifier) {
    LazyColumn(
        modifier =
            modifier
                .fillMaxSize()
                .background(
                    when (state.backgroundColor) {
                        Color.BLACK -> androidx.compose.ui.graphics.Color.Black
                        Color.WHITE -> androidx.compose.ui.graphics.Color.White
                        Color.TRANSPARENT -> androidx.compose.ui.graphics.Color.Transparent
                        Color.PINK -> androidx.compose.ui.graphics.Color.Magenta
                    })) {
            item {
                Text(
                    text = state.content,
                    modifier = GlanceModifier.padding(12.dp),
                    style = state.extractStyle(),
                )
            }
        }
}

private fun Note.extractStyle(): TextStyle =
    TextStyle(
        fontSize = textSize.sp,
        color =
            ColorProvider(
                when (textColor) {
                    Color.BLACK -> androidx.compose.ui.graphics.Color.Black
                    Color.WHITE -> androidx.compose.ui.graphics.Color.White
                    Color.TRANSPARENT -> androidx.compose.ui.graphics.Color.Transparent
                    Color.PINK -> androidx.compose.ui.graphics.Color.Magenta
                }))
