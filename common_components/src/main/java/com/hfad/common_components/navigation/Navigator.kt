package com.hfad.common_components.navigation

import android.health.connect.datatypes.ExerciseRoute
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavController

class Navigator {
    var navController: NavController? = null

    fun bind(
        navController: NavController
    ) {
        this.navController = navController
    }

    fun navigate(
        route: String
    ) {
        navController?.navigate(route)
    }

    val currentRoute: String
        get() = navController?.currentBackStackEntry?.destination?.route ?: ""

}

val LocalNavigator = staticCompositionLocalOf<Navigator> {
    error("No navigator provided")
}