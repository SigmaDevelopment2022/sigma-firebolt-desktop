package dependency_injection

import default.DefaultWindowController
import org.kodein.di.DI
import org.kodein.di.bindSingleton


val controllers = DI {
    bindSingleton(tag = "SPLASH") { DefaultWindowController() }
}