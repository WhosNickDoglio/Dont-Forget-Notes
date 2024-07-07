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

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.glance.appwidget.ExperimentalGlanceRemoteViewsApi
import com.google.android.glance.appwidget.configuration.AppWidgetConfigurationScaffold
import com.google.android.glance.appwidget.configuration.rememberAppWidgetConfigurationState
import dev.whosnickdoglio.baenotes.configuration.theme.BaeNotesTheme
import dev.whosnickdoglio.baenotes.model.Note
import dev.whosnickdoglio.baenotes.widget.BaeNoteWidget
import kotlinx.coroutines.launch

class ConfigurationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BaeNotesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize().safeDrawingPadding(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ConfigurationScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalGlanceRemoteViewsApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun ConfigurationScreen() {
    val scope = rememberCoroutineScope()
    val configurationState = rememberAppWidgetConfigurationState(BaeNoteWidget)

    // If we don't have a valid id, discard configuration and finish the activity.
    if (configurationState.glanceId == null) {
        configurationState.discardConfiguration()
        return
    }

    AppWidgetConfigurationScaffold(
        appWidgetConfigurationState = configurationState,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { scope.launch { configurationState.applyConfiguration() } }
            ) {
                Icon(imageVector = Icons.Rounded.Done, contentDescription = "Save changes")
            }
        }
    ) {
        val currentState = configurationState.getCurrentState<Note>() ?: Note()

        NewNoteScreen(
            state = currentState,
            onEvent = { event ->
                when (event) {
                    is BackgroundColorChange -> {
                        configurationState.updateCurrentState<Note> {
                            it.copy(backgroundColor = event.color)
                        }
                    }
                    is NoteContentChange -> {
                        configurationState.updateCurrentState<Note> {
                            it.copy(content = event.content)
                        }
                    }
                    is TextColorChange -> {
                        configurationState.updateCurrentState<Note> {
                            it.copy(textColor = event.color)
                        }
                    }
                    is TextSizeDecrement -> {
                        configurationState.updateCurrentState<Note> {
                            val currentTextSize = it.textSize

                            it.copy(textSize = currentTextSize - 2)
                        }
                    }
                    is TextSizeIncrement -> {
                        configurationState.updateCurrentState<Note> {
                            val currentTextSize = it.textSize
                            it.copy(textSize = currentTextSize + 2)
                        }
                    }
                }
            }
        )
    }
}
