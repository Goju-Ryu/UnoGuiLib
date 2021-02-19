package model

import androidx.compose.runtime.Composable

data class Player(
    val name: String,
    internal val picture: @Composable () -> Unit
)
