package dev.whosnickdoglio.baenotes.widget

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.glance.appwidget.testing.unit.runGlanceAppWidgetUnitTest
import androidx.glance.testing.unit.hasContentDescription
import dev.whosnickdoglio.baenotes.model.NoteWidgetState
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class BaeNoteWidgetTest {

    @Test
    fun `note appears as expected`() = runGlanceAppWidgetUnitTest {
        setAppWidgetSize(DpSize(100.dp, 100.dp))

        provideComposable { NoteWidget(state = NoteWidgetState(content = "Hello Test")) }

        onNode(hasContentDescription(NOTE_TEXT_SEMANTICS)).assertExists()
        onNode(hasContentDescription(NOTE_TEXT_SEMANTICS))
    }
}
