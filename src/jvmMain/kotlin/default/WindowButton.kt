package default

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DefaultWindowButton(
    backgroundColor: Color = Color.Transparent,
    focusedColor: Color = Color.LightGray,
    onClick: () -> Unit,
    content: @Composable BoxScope.() -> Unit
) {
    var isFocused by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .height(30.dp)
            .width(45.dp)
            .background(if (isFocused) focusedColor else backgroundColor)
            .onPointerEvent(PointerEventType.Enter) { isFocused = true }
            .onPointerEvent(PointerEventType.Exit) { isFocused = false }
            .clickable { onClick() },
        contentAlignment = Alignment.Center,
        content = content
    )
}

@Composable
fun MinimizeWindowButton(onClick: () -> Unit) {
    DefaultWindowButton(onClick = onClick) {
        Divider(
            modifier = Modifier.width(8.dp).height(1.dp),
            color = Color.Black
        )
    }
}

@Composable
fun MaximizeWindowButton(onClick: () -> Unit) {
    DefaultWindowButton(onClick = onClick) {
        Box(modifier = Modifier.size(10.dp).border(1.dp, color = Color.Black))
    }
}

@Composable
fun CloseWindowButton(onClick: () -> Unit) {
    DefaultWindowButton(onClick = onClick, focusedColor = Color.Red.copy(alpha = 0.8F)) {
        Divider(
            modifier = Modifier.height(14.dp).width(1.dp).rotate(45F),
            color = Color.Black
        )

        Divider(
            modifier = Modifier.height(14.dp).width(1.dp).rotate(-45F),
            color = Color.Black
        )
    }
}