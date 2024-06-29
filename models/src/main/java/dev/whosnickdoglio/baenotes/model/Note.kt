package dev.whosnickdoglio.baenotes.model

import kotlinx.serialization.Serializable

@Serializable
data class Note(
    val content: String = "",
    val textColor: Color = Color.WHITE,
    val backgroundColor: Color = Color.TRANSPARENT,
    val textSize: Int = 12,
)

enum class Color {
    BLACK,
    WHITE,
    TRANSPARENT,
    PINK,
}
