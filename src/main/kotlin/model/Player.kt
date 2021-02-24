package model

import androidx.compose.runtime.Composable
import ui.PlayerImages

data class Player internal constructor(
    val name: String,
    internal val picture: @Composable () -> Unit
) {
    constructor(name: String): this(name, { PlayerImages.Default() })
}
