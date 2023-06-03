package dev.whosnickdoglio.baenotes.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.SizeMode
import androidx.glance.background
import androidx.glance.currentState
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.state.GlanceStateDefinition
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import dev.whosnickdoglio.baenotes.Color as BaeColor
import dev.whosnickdoglio.baenotes.ConfigurationActivity
import dev.whosnickdoglio.baenotes.Note

object BaeNoteWidget : GlanceAppWidget() {

    override val stateDefinition: GlanceStateDefinition<*> = BaeNoteWidgetStateDefinition

    @Composable
    override fun Content() {

        val state = currentState<Note>()
        NoteWidget(state)
    }

    override val sizeMode: SizeMode = SizeMode.Exact

    @Composable
    private fun NoteWidget(state: Note, modifier: GlanceModifier = GlanceModifier) {
        Column(
            modifier =
                modifier
                    .fillMaxSize()
                    .background(
                        when (state.backgroundColor) {
                            BaeColor.BLACK -> Color.Black
                            BaeColor.WHITE -> Color.White
                            BaeColor.TRANSPARENT -> Color.Transparent
                            BaeColor.PINK -> Color.Magenta
                        }
                    )
                    .clickable(actionStartActivity<ConfigurationActivity>())
        ) {
            Text(
                text = state.content,
                modifier = GlanceModifier.padding(12.dp),
                style = state.extractStyle(),
            )
        }
    }

    private fun Note.extractStyle(): TextStyle =
        TextStyle(
            fontSize = textSize.sp,
            color =
                ColorProvider(
                    when (textColor) {
                        BaeColor.BLACK -> Color.Black
                        BaeColor.WHITE -> Color.White
                        BaeColor.TRANSPARENT -> Color.Transparent
                        BaeColor.PINK -> Color.Magenta
                    }
                )
        )
}
