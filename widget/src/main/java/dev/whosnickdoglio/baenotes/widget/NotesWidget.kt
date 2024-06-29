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
