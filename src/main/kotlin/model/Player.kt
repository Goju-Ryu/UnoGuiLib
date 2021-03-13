package model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import ui.PlayerImages

data class Player internal constructor(
    val name: String,
    internal val picture: MutableState<@Composable () -> Unit>
) {
    constructor(name: String): this(name, mutableStateOf({PlayerImages.Default()}))
}
