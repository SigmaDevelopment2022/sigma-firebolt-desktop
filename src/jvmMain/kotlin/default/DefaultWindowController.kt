package default


import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import java.awt.Dimension
import java.awt.GraphicsEnvironment
import java.awt.Rectangle
import java.util.*

class DefaultWindowController() {
    private lateinit var _windowScope: FrameWindowScope
    private lateinit var _windowState: WindowState
    private lateinit var _applicationScope: ApplicationScope
    private var _windowPadding by mutableStateOf(0.dp)
    private var _windowBorderRadius by mutableStateOf(0F)
    private var _size by mutableStateOf(DpSize(1280.dp, 720.dp))

    fun setWindowScope(windowScope: FrameWindowScope) {
        _windowScope = windowScope
    }

    fun setWindowState(windowState: WindowState) {
        _windowState = windowState
    }

    fun setApplicationScope(applicationScope: ApplicationScope) {
        _applicationScope = applicationScope
    }

    fun setSize(size: DpSize) {
        _size = size
    }

    fun getWindowScope() = _windowScope

    fun getWindowState() = _windowState

    fun getApplicationScope() = _applicationScope

    fun minimize() {
        _windowScope.window.isMinimized = true
    }

    fun restore() {
        _windowScope.window.placement = WindowPlacement.Floating
        _windowState.position = WindowPosition(alignment = Alignment.Center)
        _windowScope.window.size = Dimension(_size.width.value.toInt(), _size.height.value.toInt())
        refreshWindowBorder()
    }

    @Suppress("warnings")
    private fun _maximize() {
        _windowScope.window.placement = WindowPlacement.Maximized
        _windowScope.window.maximizedBounds = Rectangle(
            _windowScope.window.x,
            _windowScope.window.y,
            GraphicsEnvironment.getLocalGraphicsEnvironment().maximumWindowBounds.width,
            GraphicsEnvironment.getLocalGraphicsEnvironment().maximumWindowBounds.height,
        )
    }

    fun isMaximized() = _windowScope.window.placement == WindowPlacement.Maximized

    fun maximize() {
        _maximize()
        restore()
        _maximize()
        refreshWindowBorder()
    }

    fun getWindowPadding() = _windowPadding

    fun getWindowBorderRadius() = _windowBorderRadius

    fun refreshWindowBorder() {
        val osName = System.getProperty("os.name").lowercase(Locale.getDefault())
        val isFloating = _windowScope.window.placement == WindowPlacement.Floating
        _windowBorderRadius = if (osName == "windows 11" && isFloating) 10F else 0F
        _windowPadding = if (isFloating) 4.dp else 0.dp
    }

    fun close() {
        _applicationScope.exitApplication()
    }
}