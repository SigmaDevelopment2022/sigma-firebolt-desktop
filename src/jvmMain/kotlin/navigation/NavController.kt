package navigation

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable

class NavController(
    private val startDestination: String,
    private var backStackScreens: MutableSet<String> = mutableSetOf()
) {
    var currentScreen by mutableStateOf(startDestination)

    fun navigate(route: String) {
        if (route != currentScreen) {
            if (backStackScreens.contains(currentScreen) && currentScreen != startDestination) {
                backStackScreens.remove(currentScreen)
            }

            if (route == startDestination) {
                backStackScreens = mutableSetOf()
            } else {
                backStackScreens.add(currentScreen)
            }

            currentScreen = route
        }
    }

    fun navigateBack() {
        if (backStackScreens.isNotEmpty()) {
            currentScreen = backStackScreens.last()
            backStackScreens.remove(currentScreen)
        }
    }
}


@Composable
fun rememberNavController(
    startDestination: String,
    backStackScreens: MutableSet<String> = mutableSetOf()
) = rememberSaveable {
    mutableStateOf(NavController(startDestination, backStackScreens))
}.value