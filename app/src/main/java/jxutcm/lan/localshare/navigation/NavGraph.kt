package jxutcm.lan.localshare.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import jxutcm.lan.localshare.route.HomeRoute
import jxutcm.lan.localshare.screen.HomeScreen
import jxutcm.lan.localshare.screen.SettingScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val navigationItems = listOf(
        HomeRoute.Home, HomeRoute.Setting
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = "LocalShare")
        })
    }, bottomBar = {
        NavigationBar {
            navigationItems.forEach { route ->
                NavigationBarItem(icon = {
                    Icon(if (currentDestination?.hierarchy?.any { it.route == route.route } == true) route.selectedIcon
                    else route.unselectedIcon, contentDescription = null)
                },
                    label = { Text(route.title) },
                    selected = currentDestination?.hierarchy?.any { it.route == route.route } == true,
                    onClick = {
                        navController.navigate(route.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    })
            }
        }
    }) { innerPadding ->
        NavHost(
            navController, startDestination = HomeRoute.Home.route, Modifier.padding(innerPadding)
        ) {
            composable(HomeRoute.Home.route) {
                HomeScreen()
            }
            composable(HomeRoute.Setting.route) {
                SettingScreen()
            }
        }
    }
}