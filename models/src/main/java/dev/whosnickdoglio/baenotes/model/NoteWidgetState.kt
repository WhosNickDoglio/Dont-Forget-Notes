// Copyright (C) 2025 Nicholas Doglio
// SPDX-License-Identifier: MIT

package dev.whosnickdoglio.baenotes.model

import androidx.compose.runtime.Stable
import kotlinx.serialization.Serializable

@Serializable
@Stable
public data class NoteWidgetState(
    val content: String = "",
    val textColor: NoteColor = NoteColor.SYSTEM,
    val backgroundColor: NoteColor = NoteColor.SYSTEM,
    val textSize: Int = 12,
)

public enum class NoteColor {
    BLACK,
    WHITE,
    TRANSPARENT,
    PINK,
    SYSTEM,
}
