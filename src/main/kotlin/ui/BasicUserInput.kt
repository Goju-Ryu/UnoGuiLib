package ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import kotlin.properties.Delegates

/**
 * This composable creates an arbitrary number of buttons.
 * The buttens will have a standard behavior that should depend only on the supplied name
 */
@Composable
internal fun InputButtons(onInputConfirmed: OnInputConfirmed, vararg buttonNames: String) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        for (name in buttonNames) {
            Button(onClick = { onInputConfirmed(name) }, Modifier.padding(5.dp)) {
                Text(name)
            }
        }
    }
}

@Composable
internal fun InputEditText(onInputConfirmed: OnInputConfirmed, buttonText: String = "Ok", singleLine: Boolean = true) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        val text = remember { mutableStateOf("") }
        TextField(
            text.value,
            onValueChange = { text.value = it },
            singleLine = singleLine,
            modifier = Modifier
                .padding(5.dp)
                .width(400.dp)
        )
        AcceptButton({ onInputConfirmed(text.value) }, Modifier.wrapContentSize()) {
            Text(buttonText)
        }
    }
}

@Composable
internal fun InputDropDownMenu(onInputConfirmed: OnInputConfirmed, vararg options: String) {
    val expanded = remember { mutableStateOf(false) }
    val selection = remember { mutableStateOf(null as String?) }

    val flipExpanded = { expanded.value = !expanded.value }

    Box {
        DropDownMenuBar(
            onClick = flipExpanded,
            selection = selection.value,
            onInputConfirmed = onInputConfirmed
        )
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = flipExpanded, // For some reason this seems to never be called. May be a Compose problem.
        ) {
            for (option in options) {
                DropdownMenuItem(
                    onClick = { flipExpanded(); selection.value = option },
                ) {
                    Text(option)
                }
            }
        }
    }
}

@Composable
private fun DropDownMenuBar(onClick: () -> Unit, onInputConfirmed: OnInputConfirmed, selection: String?) {
    Row(
        Modifier
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick,
            Modifier
                .wrapContentSize()
                .padding(5.dp)
                .align(Alignment.CenterVertically),
            border = ButtonDefaults.outlinedBorder,
            colors = ButtonDefaults.textButtonColors()
        ) {
            Text(
                selection.orEmpty(),
                Modifier
                    .align(Alignment.CenterVertically)
                    .defaultMinSize(minWidth = 80.dp),
                maxLines = 1,
                textAlign = TextAlign.Center
            )
            Icon(Icons.Default.ArrowDropDown, "drop down button")
        }

        AcceptButton( onClick = {onInputConfirmed(selection!!)}, enabled = selection != null )
    }

}

@Composable
internal fun InputCheckBox(onInputConfirmed: (List<String>) -> Unit, vararg options: String) {
    val checkedOptions = remember{ mutableStateListOf<String>() }

    Row {
        for(option in options) {
            Row(
                Modifier.padding(horizontal = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val checked = remember { mutableStateOf(false) }
                Text(option)
                Checkbox(
                    checked = checked.value,
                    onCheckedChange = {
                        checked.value = it
                        if (it) checkedOptions.add(option) else checkedOptions.remove(option)
                    },
                    Modifier.padding(5.dp)
                )
            }
        }
        AcceptButton(onClick = {onInputConfirmed(checkedOptions)})
    }
}

@Composable
private fun AcceptButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable () -> Unit = {Text("Accept")}
) {
    Button(
        onClick = onClick,
        modifier
            .wrapContentSize()
            .padding(horizontal = 5.dp),
        enabled = enabled
    ) {
        content()
    }
}

/**
 * A special onClickListener for buttons that takes the name of a button as an argument
 */
internal typealias OnInputConfirmed = (name: String) -> Unit





















