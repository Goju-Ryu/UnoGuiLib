package ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.layout.ScaleFactor
import androidx.compose.ui.layout.times
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import model.Player
import ui.UserInput.ImageDropDownMenu

@Composable
internal fun PlayerImageCardColorSelector(playerModel: Player) { //TODO handle long player names gracefully
    remember { playerModel } //TODO make it possible to scale

    ImageDropDownMenu(
        {
            PlayerImageCard(playerModel)
        }
    ) {
        val colors = listOf(Color.Black, Color.Magenta, Color.Green)

        for (color in colors) {
            Box(
                Modifier
                    .padding(5.dp)
                    .clickable { playerModel.picture.value = { PlayerImages.Default(color = color) } }
            ) {
                PlayerImages.Default(color = color)
            }
        }
    }
}

@Composable
internal fun PlayerImageCard(playerModel: Player) {
    remember { playerModel }
    Card(Modifier.padding(5.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            playerModel.picture.value()
            Text(playerModel.name, fontStyle = MaterialTheme.typography.caption.fontStyle, fontSize = 16.sp)
        }
    }
}

object PlayerImages {
    @Composable
    internal fun Default(size: Dp = 100.dp, color: Color = Color.Black) {
        remember { size }
        Canvas(Modifier.size(size).border(2.dp, Color.Black)) {
            val height = this.size.height
            val width = this.size.width
            inset(0f, size.value.div(3), 0f, 0f) { drawBody(this, color) }
            inset(width.div(4), 0f, width.div(4), bottom = height.div(2)) { drawHead(this) }
        }
    }

    private fun drawHead(scope: DrawScope) {
        scope.drawOval(
            color = Color.Yellow
        )
    }

    private fun drawBody(scope: DrawScope, color: Color = Color.Black) {
        scope.drawArc(
            color = color,
            startAngle = 0f,
            sweepAngle = -180f,
            useCenter = false,
            size = scope.size.times(ScaleFactor(1f, 2f))
        )


    }
}