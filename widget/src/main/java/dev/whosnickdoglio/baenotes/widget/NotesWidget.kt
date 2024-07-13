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

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.background
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import dev.whosnickdoglio.baenotes.model.Color as NoteColor
import dev.whosnickdoglio.baenotes.model.NoteWidgetState

@Composable
internal fun NoteWidget(state: NoteWidgetState, modifier: GlanceModifier = GlanceModifier) {
    LazyColumn(
        modifier =
            modifier
                .fillMaxSize()
                .background(
                    state.backgroundColor.toComposeColor(GlanceTheme.colors.widgetBackground))) {
            item {
                Text(
                    text = state.content,
                    modifier = GlanceModifier.padding(12.dp),
                    style =
                        TextStyle(
                            fontSize = state.textSize.sp,
                            color =
                                state.textColor.toComposeColor(GlanceTheme.colors.onBackground)),
                )
            }
        }
}

private fun NoteColor.toComposeColor(system: ColorProvider): ColorProvider =
    when (this) {
        NoteColor.BLACK -> ColorProvider(Color.Black)
        NoteColor.WHITE -> ColorProvider(Color.White)
        NoteColor.TRANSPARENT -> ColorProvider(Color.Transparent)
        NoteColor.PINK -> ColorProvider(Color.Magenta)
        NoteColor.SYSTEM -> system
    }
