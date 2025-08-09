package edu.bluejack23_2.to_LIst.ui.navbar

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.NavigationBar as BottomNavigation
import androidx.compose.material3.NavigationBarItem as BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector // Needed for the ImageVector type in your BottomNavItem
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.bluejack23_2.to_LIst.ui.theme.UISettings


@Composable
fun BottomNavigationBar(navController: NavController, currentRoute: String?, containerColor: Color = UISettings.bottomNavBarColor,) {
    BottomNavigation (containerColor = containerColor){
        BottomNavItem.values().forEach { item:BottomNavItem ->
            if(item.route == "empty"){
                Spacer(modifier = Modifier.width(60.dp))
            }else{
                BottomNavigationItem(
                    icon = { Icon(item.icon, contentDescription = item.label) },
                    label = { Text(item.label) },
                    selected = currentRoute == item.route,
                    onClick = {
                        // Navigate to the selected screen
                        navController.navigate(item.route) {
                            // Prevent adding the same destination to the back stack multiple times
                            launchSingleTop = true
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            restoreState = true
                        }
                    }
                )
            }

        }

    }
}

