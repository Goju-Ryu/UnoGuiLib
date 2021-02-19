package ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.graphics.vector.Path
import androidx.compose.ui.graphics.vector.PathBuilder
import androidx.compose.ui.graphics.vector.PathData
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.layout.ScaleFactor
import androidx.compose.ui.layout.times
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import model.Player as PlayerModel

@Composable
internal fun Player(playerModel: PlayerModel){
    remember { playerModel }
    Card(Modifier.padding(5.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            playerModel.picture()
            Text(playerModel.name, fontStyle = MaterialTheme.typography.caption.fontStyle)
        }
    }
}

object PlayerImages {
    @Composable
    internal fun PlayerMan(size: Dp = 50.dp) {
        remember { size }
        Canvas(Modifier.size(size).border(2.dp, Color.Black)) {
            val height = this.size.height
            val width = this.size.width
            inset(0f, size.value.div(3),0f,0f) { drawBody(this) }
            inset(width.div(4), 0f,width.div(4), bottom = height.div(2)){ drawHead(this) }
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