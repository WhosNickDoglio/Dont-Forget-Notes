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

package dev.whosnickdoglio.baenotes.configuration

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.whosnickdoglio.baenotes.configuration.components.ColorRadioGroup
import dev.whosnickdoglio.baenotes.configuration.components.TextSizeSelector
import dev.whosnickdoglio.baenotes.configuration.theme.BaeNotesTheme
import dev.whosnickdoglio.baenotes.model.Color
import dev.whosnickdoglio.baenotes.model.Note
import kotlinx.collections.immutable.persistentListOf

sealed interface Event

data class NoteContentChange(val content: String) : Event

data class TextColorChange(val color: Color) : Event

data class BackgroundColorChange(val color: Color) : Event

data object TextSizeIncrement : Event

data object TextSizeDecrement : Event

@Composable
fun NewNoteScreen(
    state: Note,
    onEvent: (Event) -> Unit,
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState(),
) {
    Column(modifier = modifier.verticalScroll(scrollState).fillMaxSize()) {
        Text(
            text = "Aye bae bae! Let me take a note for you!",
            fontSize = 36.sp,
            modifier = Modifier.padding(12.dp),
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            value = state.content,
            onValueChange = { note -> onEvent(NoteContentChange(note)) },
            placeholder = { Text(text = "Note content...") },
        )

        Spacer(modifier = Modifier.height(20.dp))

        ColorRadioGroup(
            title = "Text Color",
            options = persistentListOf(Color.BLACK, Color.WHITE, Color.PINK),
            onSelected = { onEvent(TextColorChange(it)) },
            currentSelection = state.textColor
        )

        Spacer(modifier = Modifier.height(20.dp))

        ColorRadioGroup(
            title = "Background Color",
            options = persistentListOf(Color.TRANSPARENT, Color.BLACK, Color.WHITE, Color.PINK),
            onSelected = { onEvent(BackgroundColorChange(it)) },
            currentSelection = state.backgroundColor
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextSizeSelector(
            textSize = state.textSize.toString(),
            onDecrement = { onEvent(TextSizeDecrement) },
            onIncrement = { onEvent(TextSizeIncrement) }
        )

        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Preview
@Composable
private fun PreviewNewNoteScreen() {
    BaeNotesTheme { NewNoteScreen(state = Note(), onEvent = {}) }
}
