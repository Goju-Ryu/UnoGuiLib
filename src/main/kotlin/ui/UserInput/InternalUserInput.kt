package ui.UserInput

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyGridScope
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier


@Composable
internal fun ImageDropDownMenu(clickable: @Composable () -> Unit, options: @Composable ColumnScope.() -> Unit) {
    val expanded = remember { mutableStateOf(false) }
    val flipExpanded = { expanded.value = !expanded.value }

    ImageDropDownMenu(clickable, expanded.value, flipExpanded, options)
}

@Composable
internal fun ImageDropDownMenu(
    clickable: @Composable () -> Unit,
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    options: @Composable ColumnScope.() -> Unit
) {
    remember { expanded }


    Box(Modifier.clickable { onDismissRequest() }) {
        clickable()

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = onDismissRequest, // For some reason this seems to never be called. May be a Compose problem.
            content = options,
            modifier = Modifier.background(LocalContentColor.current.copy(0.2f))
        )
    }
}