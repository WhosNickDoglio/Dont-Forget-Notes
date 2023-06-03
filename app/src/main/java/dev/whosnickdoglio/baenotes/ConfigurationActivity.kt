package dev.whosnickdoglio.baenotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
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
import dev.whosnickdoglio.baenotes.ui.theme.BaeNotesTheme
import dev.whosnickdoglio.baenotes.widget.BaeNoteWidget
import kotlinx.coroutines.launch

class ConfigurationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaeNotesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
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
