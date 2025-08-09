package edu.bluejack23_2.to_LIst.ui.pages.ScheduledTaskPage.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import edu.bluejack23_2.to_LIst.ui.theme.UISettings

@Composable
fun ScheduledPageTitle(){

    Column (modifier = Modifier.padding(bottom = 16.dp, top = 32.dp)){
        Text(text = "Scheduled", fontWeight = FontWeight.Bold, color = UISettings.primaryColor, fontSize = UISettings.titleFontSizeSecondary)

    }
}