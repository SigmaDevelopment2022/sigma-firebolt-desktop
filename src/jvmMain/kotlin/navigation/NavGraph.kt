package navigation

import androidx.compose.runtime.Composable

class NavGraph(
    val navController: NavController,
    val content: @Composable Builder.() -> Unit
) {
    @Composable
    fun build() = Builder().render()

    inner class Builder(val navController: NavController = this@NavGraph.navController) {
        @Composable
        fun render() = this@NavGraph.content(this)
    }
}

@Composable
fun NavGraph.Builder.composable(route: String, content: @Composable () -> Unit) {
    if (this.navController.currentScreen == route) {
        content()
    }
}