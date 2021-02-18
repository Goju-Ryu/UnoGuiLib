package ui

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material.DropdownMenu as MaterialDropdownMenu

/**
 * This composable creates an arbitrary number of buttons.
 * The buttens will have a standard behavior that should depend only on the supplied name
 */
@Composable
internal fun Buttons(onInputConfirmed: OnInputConfirmed, vararg buttonNames: String) {
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
internal fun EditText(onInputConfirmed: OnInputConfirmed, buttonText: String = "Ok", singleLine: Boolean = true) {
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
        Button({ onInputConfirmed(text.value) }, Modifier.wrapContentSize()) {
            Text(buttonText)
        }
    }
}

@Composable
internal fun DropDownMenu(onInputConfirmed: OnInputConfirmed, vararg options: String) {
    TODO("Yet to be implemented")
}

@Composable
internal fun DropDownMenuBar(onClick: () -> Unit, selection: String, onInputConfirmed: OnInputConfirmed) {
    Row(Modifier.padding(5.dp).wrapContentSize().clickable { onClick(); println("1") }) {
        Text(selection, Modifier.align(Alignment.CenterVertically), maxLines = 1)
        IconButton({}) {
            Icon(Icons.Default.ArrowDropDown, "drop down button")
        }

        Button(onClick = {onInputConfirmed(selection)}, Modifier.wrapContentSize()) {
            Text("Accept")
        }
    }

}


/**
 * A special onClickListener for buttons that takes the name of a button as an argument
 */
internal typealias OnInputConfirmed = (name: String) -> Unit