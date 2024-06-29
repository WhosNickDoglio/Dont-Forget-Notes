package dev.whosnickdoglio.baenotes

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.whosnickdoglio.baenotes.ui.theme.BaeNotesTheme
import kotlinx.collections.immutable.ImmutableList
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
    Column(modifier = modifier.fillMaxSize().verticalScroll(scrollState)) {
        Text(
            text = "Aye bae bae! Let me take a note for you!",
            fontSize = 36.sp,
            modifier = Modifier.padding(12.dp),
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            value = state.content,
            onValueChange = { note -> onEvent(NoteContentChange(note)) }
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
    }
}

@Composable
private fun ColorRadioGroup(
    title: String,
    options: ImmutableList<Color>,
    currentSelection: Color,
    onSelected: (Color) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(text = title)
        options.forEach {
            RadioButtonRow(
                isSelected = it == currentSelection,
                title = it.toString(),
                onSelected = { onSelected(it) }
            )
        }
    }
}

@Composable
private fun RadioButtonRow(
    isSelected: Boolean,
    title: String,
    onSelected: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier =
            modifier.selectable(
                selected = isSelected,
                role = Role.RadioButton,
                onClick = onSelected
            )
    ) {
        RadioButton(selected = isSelected, onClick = null)
        Text(text = title)
    }
}

@Composable
fun TextSizeSelector(
    textSize: String,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        Text(text = "Text Size")

        Row(verticalAlignment = Alignment.CenterVertically) {
            Button(onClick = onDecrement) { Text(text = "-") }

            Text(text = textSize, modifier = Modifier.padding(horizontal = 4.dp), fontSize = 20.sp)

            Button(onClick = onIncrement) { Text(text = "+") }
        }
    }
}

@Preview
@Composable
private fun PreviewNewNoteScreen() {
    BaeNotesTheme { NewNoteScreen(state = Note(), onEvent = {}) }
}
