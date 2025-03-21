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

package dev.whosnickdoglio.baenotes.configuration.internal

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
import dev.whosnickdoglio.baenotes.configuration.internal.components.ColorRadioGroup
import dev.whosnickdoglio.baenotes.configuration.internal.components.TextSizeSelector
import dev.whosnickdoglio.baenotes.configuration.internal.theme.BaeNotesTheme
import dev.whosnickdoglio.baenotes.model.NoteColor
import dev.whosnickdoglio.baenotes.model.NoteWidgetState
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
internal fun NewNoteScreen(
    state: NoteWidgetState,
    onEvent: (NoteEvent) -> Unit,
    modifier: Modifier = Modifier,
    defaultTextColors: ImmutableList<NoteColor> =
        persistentListOf(NoteColor.BLACK, NoteColor.WHITE, NoteColor.PINK, NoteColor.SYSTEM),
    defaultBackgroundColors: ImmutableList<NoteColor> =
        persistentListOf(NoteColor.TRANSPARENT, NoteColor.BLACK, NoteColor.WHITE, NoteColor.PINK, NoteColor.SYSTEM),
    scrollState: ScrollState = rememberScrollState(),
) {
    Column(modifier = modifier
        .verticalScroll(scrollState)
        .fillMaxSize()) {
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            value = state.content,
            onValueChange = { note -> onEvent(NoteEvent.ContentChange(note)) },
            placeholder = { Text(text = "Note content...") },
        )

        Spacer(modifier = Modifier.height(20.dp))

        ColorRadioGroup(
            title = "Text Color",
            options = defaultTextColors,
            onSelected = { onEvent(NoteEvent.TextColorChange(it)) },
            currentSelection = state.textColor
        )

        Spacer(modifier = Modifier.height(20.dp))

        ColorRadioGroup(
            title = "Background Color",
            options = defaultBackgroundColors,
            onSelected = { onEvent(NoteEvent.BackgroundColorChange(it)) },
            currentSelection = state.backgroundColor
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextSizeSelector(
            textSize = state.textSize.toString(),
            onDecrement = { onEvent(NoteEvent.TextSizeDecrement) },
            onIncrement = { onEvent(NoteEvent.TextSizeIncrement) })

        Spacer(modifier = Modifier.height(40.dp))
    }
}

internal sealed interface NoteEvent {
    data class ContentChange(val content: String) : NoteEvent

    data class TextColorChange(val color: NoteColor) : NoteEvent

    data class BackgroundColorChange(val color: NoteColor) : NoteEvent

    data object TextSizeIncrement : NoteEvent

    data object TextSizeDecrement : NoteEvent
}

@Preview
@Composable
private fun PreviewNewNoteScreen() {
    BaeNotesTheme { NewNoteScreen(state = NoteWidgetState(), onEvent = {}) }
}
