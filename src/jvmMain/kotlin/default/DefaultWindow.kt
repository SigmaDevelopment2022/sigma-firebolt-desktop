package default

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*

@Composable
fun ApplicationScope.DefaultWindow(
    controller: DefaultWindowController,
    size: DpSize = DpSize(1280.dp, 720.dp),
    content: @Composable FrameWindowScope.() -> Unit
) {

    val state = rememberWindowState(
        size = size,
        placement = WindowPlacement.Floating,
        isMinimized = false,
        position = WindowPosition(alignment = Alignment.Center)
    )

    Window(
        onCloseRequest = ::exitApplication,
        undecorated = true,
        transparent = true,
        resizable = false,
        state = state
    ) {
        controller.setApplicationScope(this@DefaultWindow)
        controller.setWindowScope(this)
        controller.setWindowState(state)
        controller.setSize(size)
        controller.refreshWindowBorder()
        val shape = RoundedCornerShape(controller.getWindowBorderRadius())

        Surface(
            color = Color.LightGray,
            shape = shape,
            elevation = 3.dp,
            modifier = Modifier
                .padding(controller.getWindowPadding())
                .fillMaxSize()
                .border(BorderStroke(1.dp, color = Color.Black.copy(alpha = 0.8F)), shape)
        ) { content() }
    }
}