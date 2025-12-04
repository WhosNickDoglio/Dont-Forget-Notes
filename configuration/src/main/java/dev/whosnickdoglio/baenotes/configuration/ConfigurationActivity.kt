// Copyright (C) 2025 Nicholas Doglio
// SPDX-License-Identifier: MIT

package dev.whosnickdoglio.baenotes.configuration

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.glance.appwidget.ExperimentalGlanceRemoteViewsApi
import com.google.android.glance.appwidget.configuration.AppWidgetConfigurationScaffold
import com.google.android.glance.appwidget.configuration.rememberAppWidgetConfigurationState
import dev.whosnickdoglio.baenotes.configuration.internal.NewNoteScreen
import dev.whosnickdoglio.baenotes.configuration.internal.NoteEvent
import dev.whosnickdoglio.baenotes.configuration.internal.theme.BaeNotesTheme
import dev.whosnickdoglio.baenotes.model.NoteWidgetState
import dev.whosnickdoglio.baenotes.widget.BaeNoteWidget
import kotlinx.coroutines.launch

public class ConfigurationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BaeNotesTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .safeDrawingPadding(),
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
                Icon(
                    painter = painterResource(R.drawable.done_check),
                    contentDescription = "Save changes"
                )
            }
        }
    ) {
        val currentState =
            configurationState.getCurrentState<NoteWidgetState>() ?: NoteWidgetState()

        NewNoteScreen(
            state = currentState,
            onEvent = { event ->
                when (event) {
                    is NoteEvent.BackgroundColorChange -> {
                        configurationState.updateCurrentState<NoteWidgetState> { note ->
                            note.copy(backgroundColor = event.color)
                        }
                    }

                    is NoteEvent.ContentChange -> {
                        configurationState.updateCurrentState<NoteWidgetState> { note ->
                            note.copy(content = event.content)
                        }
                    }

                    is NoteEvent.TextColorChange -> {
                        configurationState.updateCurrentState<NoteWidgetState> { note ->
                            note.copy(textColor = event.color)
                        }
                    }

                    is NoteEvent.TextSizeDecrement -> {
                        configurationState.updateCurrentState<NoteWidgetState> { note ->
                            val currentTextSize = note.textSize
                            note.copy(textSize = currentTextSize - 2)
                        }
                    }

                    is NoteEvent.TextSizeIncrement -> {
                        configurationState.updateCurrentState<NoteWidgetState> { note ->
                            val currentTextSize = note.textSize
                            note.copy(textSize = currentTextSize + 2)
                        }
                    }
                }
            }
        )
    }
}
