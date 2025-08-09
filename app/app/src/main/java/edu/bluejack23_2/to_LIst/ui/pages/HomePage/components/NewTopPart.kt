package edu.bluejack23_2.to_LIst.ui.pages.HomePage.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AllInbox
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.CheckBox
import androidx.compose.material.icons.outlined.DoneAll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import edu.bluejack23_2.to_LIst.ui.theme.UISettings


@Composable
fun NewTopPart(navController: NavController){
    Column (modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){
        Row {
            // Today
            TopPartBox(onButtonClick = {
                navController.navigate("today-to-do-list")
            },
                color = UISettings.homeButtonColor1,
                icon = Icons.Outlined.CalendarToday,
                text = "Today"
            )
            Spacer(modifier = Modifier.width(15.dp))
            // Scheduled
            TopPartBox(onButtonClick = {
                navController.navigate("schedule-to-do-list")
            },
                color = UISettings.homeButtonColor2,
                icon = Icons.Outlined.CalendarMonth,
                text = "Scheduled"
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        Row {
            // All
            TopPartBox(onButtonClick = {
                navController.navigate("show-all")
            },
                color = UISettings.homeButtonColor3,
                icon = Icons.Outlined.AllInbox,
                text = "All"
                )
            Spacer(modifier = Modifier.width(15.dp))
            // Completed
            TopPartBox(onButtonClick = {
                navController.navigate("show-completed")
            },
                color = UISettings.homeButtonColor4,
                icon = Icons.Outlined.CheckBox,
                text = "Completed"
            )

        }



    }

}