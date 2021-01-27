import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.unit.IntSize
import ui.Buttons

fun main() = Window(
    title = "My Uno Game",
    size = IntSize(1280, 1024)
) {
    App()
}

@Composable
fun App() {
    var text by remember { mutableStateOf("Hello, World!") }

    MaterialTheme {
        Column {
            Button(onClick = {
                text = "Hello, Desktop!"
            }) {
                Text(text)
            }

            Buttons("Hello", "Hi", "Hola") {name -> println(name) }
        }
    }
}
