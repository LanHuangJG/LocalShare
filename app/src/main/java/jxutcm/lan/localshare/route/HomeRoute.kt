package jxutcm.lan.localshare.route

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.twotone.Home
import androidx.compose.material.icons.twotone.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class HomeRoute (
    val route:String,
    val title:String,
    val selectedIcon:ImageVector,
    val unselectedIcon:ImageVector
){
data object Home:HomeRoute("home","主页", selectedIcon = Icons.TwoTone.Home, unselectedIcon = Icons.Outlined.Home)
data object Setting:HomeRoute("setting","设置", selectedIcon = Icons.TwoTone.Settings, unselectedIcon = Icons.Outlined.Settings)
}