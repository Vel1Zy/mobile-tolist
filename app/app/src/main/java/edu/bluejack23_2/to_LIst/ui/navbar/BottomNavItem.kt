package edu.bluejack23_2.to_LIst.ui.navbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Task
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PeopleAlt
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import org.checkerframework.common.subtyping.qual.Bottom

sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {

    //Bagian ini contoh aja
//    object Lists : BottomNavItem("lists", Icons.Filled.List, "Lists")
//    object Tasks : BottomNavItem("tasks/{todoListId}", Icons.Filled.Task, "Tasks")
    //Bagian ini contoh aja


    object Home : BottomNavItem("home", Icons.Outlined.Home, "Home")
    object CollabToDoList : BottomNavItem("category", Icons.Outlined.PeopleAlt, "Categories")
    object Calendar : BottomNavItem("schedule-to-do-list", Icons.Outlined.CalendarMonth, "Calendar")
    object Empty : BottomNavItem("empty", Icons.Outlined.CalendarMonth, "Calendar")

    object Profile : BottomNavItem("profile", Icons.Outlined.Person, "Profile")
    companion object {
        fun values(): Array<BottomNavItem> = arrayOf(Home, CollabToDoList, Empty, Calendar, Profile)
    }
}
